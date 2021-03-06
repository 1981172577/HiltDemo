package com.migrsoft.hiltdemo.repository.impl;

import android.os.Build;

import androidx.annotation.Nullable;


import com.migrsoft.hiltdemo.bean.User;
import com.migrsoft.hiltdemo.http.cache.Editor;
import com.migrsoft.hiltdemo.repository.CacheRepository;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;


public class CacheRepositoryImpl implements CacheRepository {
    private final Map<String, SerializableWrap> memCache = new ConcurrentHashMap<>();
    private String mCacheDir;
    private ExecutorService mPool;

    public CacheRepositoryImpl(String cacheDir) {
        this.mCacheDir = cacheDir;
        mPool = Executors.newSingleThreadExecutor();
    }

    public CacheRepositoryImpl(String cacheDir, ExecutorService workersPool) {
        this.mCacheDir = cacheDir;
        this.mPool = workersPool;
    }


    @Override
    public Editor<Serializable> put(User user) {
        String key = getKey(User.class);
        return put(key, user);
    }

    @Override
    public User getLoginedUser() {
        String key = getKey(User.class);
        Serializable serializable = getAsSerializable(key);
        return serializable instanceof User ? (User) serializable : null;
    }

    @Override
    public Integer[] getUserRoleIds() {
        String key = USER_ROLES_ID_KEY;
        Serializable serializable = getAsSerializable(key);
        return serializable instanceof Integer[] ? ((Integer[]) serializable) : new Integer[0];
    }

    @Override
    public Editor<Serializable> put(String key, Serializable value) {
        return new SerializableEditor(key, value);
    }

    @Override
    public Serializable getAsSerializable(String key) {
        SerializableWrap serializableWrap = memCache.get(key);
        if (serializableWrap == null) {
            serializableWrap = getSerializableWrap(key);
        }
        Serializable result = serializableWrap != null ? serializableWrap.getData() : null;
        if (result == null) {
            remove(key);
        } else {
            memCache.put(key, serializableWrap);
        }
        return result;
    }

    private synchronized SerializableWrap getSerializableWrap(String key) {
        try {
            return new ObjectReader(mCacheDir, key).call();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public synchronized boolean remove(String key) {
        try {
            memCache.remove(key);
            mPool.execute(new ObjectRemover(this.mCacheDir, key));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public synchronized boolean removeImmediately(String key) {
        try {
            memCache.remove(key);
            new ObjectRemover(this.mCacheDir, key).run();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void release() {
        //TODO:????????????
        memCache.clear();
        mPool.shutdownNow();
    }

    private String getKey(Class cls) {
        return cls.getName();
    }

    /**
     * ?????????
     *
     * @author ?????????249346528@qq.com??? 15/11/17
     */
    private static abstract class BaseObjectJob {
        protected String dir;

        public BaseObjectJob(String dir) {
            this.dir = dir;
        }

        private String hashKeyForDisk(String key) {
            String cacheKey;
            try {
                final MessageDigest mDigest = MessageDigest.getInstance("MD5");
                mDigest.update(key.getBytes());
                cacheKey = bytesToHexString(mDigest.digest());
            } catch (NoSuchAlgorithmException e) {
                cacheKey = String.valueOf(key.hashCode());
            }
            return cacheKey;
        }

        public String getCacheDirPath() {
            return dir;
        }

        /**
         * @param bytes
         * @return
         */
        private String bytesToHexString(byte[] bytes) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                String hex = Integer.toHexString(0xFF & bytes[i]);
                if (hex.length() == 1) {
                    sb.append('0');
                }
                sb.append(hex);
            }
            return sb.toString();
        }

        /**
         * ??????Key?????????????????????
         *
         * @param key
         * @return
         */
        protected File getCacheFile(String key) {
            File file = new File(dir);
            if(!file.exists()){
                file.mkdirs();
            }
            String fileName = hashKeyForDisk(key);
            return new File(dir, fileName);
        }
    }//BaseObjectJob

    /**
     * ?????????????????????
     *
     * @author ?????????249346528@qq.com??? 15/11/17
     */
    private static class ObjectRemover extends BaseObjectJob implements Runnable {
        private String key = null;

        public ObjectRemover(String dir, String key) {
            super(dir);
            this.key = key;
        }

        @Override
        public void run() {
            if (key == null || key.length() == 0) {
                removeAll();
            } else {
                removeByKey(key);
            }
        }

        private void removeByKey(String key) {
            File cacheFile = getCacheFile(key);
            if (cacheFile.exists()) {
                cacheFile.delete();
            }
        }

        private void removeAll() {
            for (File file : new File(getCacheDirPath()).listFiles()) {
                if (file.exists()) {
                    file.delete();
                }
            }
        }
    }//end ObjectRemover

    /**
     * ????????????????????????{@link Serializable}??????
     *
     * @author ?????????249346528@qq.com??? 14/11/17
     */
    private static class ObjectWriter extends BaseObjectJob implements Runnable {
        private Map<String, Serializable> tMap;
        /**
         * ????????????
         */
        private long expireTimeMillis = 0L;

        public ObjectWriter(String dirPath, Map<String, Serializable> tMap) {
            super(dirPath);
            this.tMap = tMap;
        }

        public void setExpireTimeMillis(long expireTimeMillis) {
            this.expireTimeMillis = expireTimeMillis;
        }

        @Override
        public void run() {
            if (tMap != null && tMap.size() > 0) {
                Iterator<Map.Entry<String, Serializable>> it = tMap.entrySet().iterator();
                while (it.hasNext()) {
                    writeObject(it.next());
                }
            }
        }

        /**
         * ???????????????{@link Serializable} ??????
         *
         * @param entry ?????????????????? {@link Serializable} ????????????
         */
        private void writeObject(Map.Entry<String, Serializable> entry) {
            File cacheFile = getCacheFile(entry.getKey());
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(cacheFile);
                byte[] serializableBytes = getBytes(entry.getValue());
                fileOutputStream.write(appendExpire(serializableBytes));
                fileOutputStream.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    fileOutputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * ??? {@link Serializable}??????????????? {@link Byte[]}
         *
         * @param s ????????????
         * @return byte[]
         */
        private byte[] getBytes(Serializable s) {
            ByteArrayOutputStream outputStream = null;
            ObjectOutputStream oos = null;
            try {
                outputStream = new ByteArrayOutputStream();
                oos = new ObjectOutputStream(outputStream);
                oos.writeObject(s);
                oos.flush();
                return outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    oos.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        /**
         * ????????????????????????
         *
         * @param serializableBytes ????????????{@link Serializable}???????????????????????????????????????
         * @return ???????????????????????????byte[]
         */
        private byte[] appendExpire(byte[] serializableBytes) {
            //???????????????
            byte[] expireBytes = new byte[83];
            //??????separator
            expireBytes[82] = '#';
            byte[] tem = getBytes(expireTimeMillis);
            if (tem != null) {
                System.arraycopy(tem, 0, expireBytes, 0, expireBytes.length - 1);
            }

            byte[] result = new byte[serializableBytes.length + expireBytes.length];
            System.arraycopy(expireBytes, 0, result, 0, expireBytes.length);
            System.arraycopy(serializableBytes, 0, result, expireBytes.length, serializableBytes.length);
            return result;
        }
    }//end ObjectWriter

    /**
     * ?????????????????????????????????{@link Serializable}??????
     *
     * @author ?????????249346528@qq.com??? 14/11/17
     */
    private static class ObjectReader extends BaseObjectJob implements Callable<SerializableWrap> {
        private String key;

        public ObjectReader(String dirPath, String key) {
            super(dirPath);
            this.key = key;
        }

        @Override
        public SerializableWrap call() throws Exception {
            byte[] originData = readSerializableByte();
            if (originData == null || originData.length <= 0) {
                return null;
            }
            Object o_expire = readserializableObject(getExpireData(originData));
            long expire = o_expire == null ? 0L : (long) o_expire;
            if (expire > 0 && expire < System.currentTimeMillis()) {
                deleteExpiredFile();
                return null;
            }
            byte[] objectBytes = new byte[originData.length - 83];
            System.arraycopy(originData, 83, objectBytes, 0, objectBytes.length);
            Serializable object = readserializableObject(objectBytes);
            return new SerializableWrap(object, expire);
        }

        /**
         * ????????????????????????
         */
        private void deleteExpiredFile() {
            File f = getCacheFile(key);
            if (f.exists()) {
                f.delete();
            }
        }

        /**
         * ????????????????????????
         *
         * @param originData ????????????
         * @return byte[]
         */
        private byte[] getExpireData(byte[] originData) {
            if (originData == null || originData.length <= 83) {
                return null;
            }
            if (originData[82] == '#') {
                byte[] result = new byte[82];
                System.arraycopy(originData, 0, result, 0, 82);
                return result;
            }
            return null;
        }

        /**
         * ??????{@link Serializable}???????????? ???????????????????????????
         *
         * @param data ????????????????????????????????????
         * @return
         */
        private Serializable readserializableObject(byte[] data) {
            if (data == null) {
                return null;
            }
            ByteArrayInputStream bais = null;
            ObjectInputStream ois = null;
            try {
                bais = new ByteArrayInputStream(data);
                ois = new ObjectInputStream(bais);
                Object reObject = ois.readObject();
                return ((Serializable) reObject);
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    if (bais != null) {
                        bais.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    if (ois != null) {
                        ois.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private byte[] readSerializableByte() {
            File cacheFile = getCacheFile(key);
            if (cacheFile == null || !cacheFile.exists()) {
                return null;
            }
            FileInputStream inputStream = null;
            ByteArrayOutputStream outputStream = null;
            try {
                inputStream = new FileInputStream(cacheFile);
                outputStream = new ByteArrayOutputStream();
                byte[] buffer = new byte[1024 * 8];
                int len;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                return outputStream.toByteArray();
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    outputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }

                try {
                    inputStream.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }//end ObjectReader

    /**
     * ?????????
     *
     * @author ?????????249346528@qq.com??? 15/11/17
     */
    private static class SerializableWrap {
        private Serializable data;
        private long expire;

        public SerializableWrap(Serializable data, long expire) {
            this.data = data;
            this.expire = expire;
        }

        public Serializable getData() {
            long curTime = System.currentTimeMillis();
            return expire == 0 || expire > curTime ? data : null;
        }

        public long getExpire() {
            return expire;
        }
    }//end SerializableWrap



    /**
     * ?????????
     *
     * @author ?????????249346528@qq.com??? 15/11/17
     */
    private class SerializableEditor implements Editor<Serializable> {
        private Map<String, Serializable> tMap = new ConcurrentHashMap<>();
        private ObjectWriter writer;
        private long expireTimeMillis = 0L;

        public SerializableEditor(String key, Serializable value) {
            tMap.put(key, value);
            writer = new ObjectWriter(mCacheDir, tMap);
        }

        @Override
        public Editor<Serializable> put(String key, Serializable value) {
            tMap.put(key, value);
            return this;
        }

        @Override
        public Editor expire(TimeUnit unit, int time) {
            expireTimeMillis = System.currentTimeMillis() + unit.toMillis(time);
            writer.setExpireTimeMillis(expireTimeMillis);
            return this;
        }

        @Override
        public boolean commit() {
            try {
                writeMem();
                writer.run();
                return true;
            } catch (Exception e) {
                e.printStackTrace();
                return false;
            }
        }

        @Override
        public void apply() {
            writeMem();
            mPool.execute(writer);
        }

        private void writeMem() {
            Iterator<String> it = tMap.keySet().iterator();
            while (it.hasNext()) {
                String key = it.next();
                memCache.put(key, new SerializableWrap(tMap.get(key), expireTimeMillis));
            }
        }

        @Override
        public Observable<Boolean> subscribe() {
            return Observable.just(commit());
        }
    }//end SerializableEditor
}
