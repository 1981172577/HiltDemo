package com.migrsoft.hiltdemo.base;

import androidx.annotation.CallSuper;
import androidx.annotation.Nullable;


import com.migrsoft.hiltdemo.bean.User;
import com.migrsoft.hiltdemo.repository.CacheRepository;
import com.migrsoft.hiltdemo.repository.Repository;
import com.migrsoft.hiltdemo.utils.DateFormatUtils;

import java.util.Date;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * 简介：
 *
 * @author 王强（249346528@qq.com） 2017/9/11.
 */

public class BaseRepository implements Repository {

    protected CacheRepository cacheRepository;

    protected CompositeDisposable compositeDisposable = new CompositeDisposable();

    public BaseRepository(CacheRepository cacheRepository) {
        this.cacheRepository = cacheRepository;
    }

    public User getUser(){
        return cacheRepository.getLoginedUser();
    }

    protected String formatDate(@Nullable Date date){
        if (date == null)
            return null;
        return DateFormatUtils.formatDate(date,DateFormatUtils.PATTERN_YEAR_MONTH_DATE);
    }

    /**
     * @param d
     * @return true未关闭
     */
    protected boolean isUnDisposed(Disposable d) {
        return d != null && !d.isDisposed();
    }

    /**
     * 执行关闭
     *
     * @param d
     */
    protected void doDispose(Disposable d) {
        if (d != null) {
            compositeDisposable.remove(d);
        }
        if (isUnDisposed(d)) {
            d.dispose();
        }
    }

    @Override
    @CallSuper
    public void release() {
        compositeDisposable.dispose();
    }
}
