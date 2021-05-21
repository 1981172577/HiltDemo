package com.migrsoft.hiltdemo.bean;

import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;



import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.List;



public class User implements Parcelable, Externalizable {

    private static final long serialVersionUID = 879878978176556456L;

    private long id;              // bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '记录编号',
    private long vendorId;        // bigint(20) NOT NULL COMMENT '所属商家ID',
    private int userType;         // int(11) NOT NULL COMMENT '帐号类型1主帐号 0子帐号',
    private String userName;      // varchar(32) NOT NULL COMMENT '登录用户名',
    private String pwd;           // varchar(32) NOT NULL COMMENT '登录密码',
    private String realName;      // varchar(32) DEFAULT NULL COMMENT '姓名',
    private String cellPhone;     // varchar(16) DEFAULT NULL COMMENT '手机号',
    private String email;         // varchar(32) DEFAULT NULL COMMENT '邮箱地址',
    private String orgCode;       // varchar(32) DEFAULT NULL COMMENT '所属组织机构',
    private String storeCode;     // varchar(32) DEFAULT NULL COMMENT '所属门店编码',
    private String depCode;       // varchar(32) DEFAULT NULL COMMENT '所属部门',
    private String memo;          // varchar(64) DEFAULT NULL COMMENT '备注',
    private int userStatus;       // int(11) NOT NULL COMMENT '用户状态 0正常 1冻结',
    private int df;               // int(11) NOT NULL COMMENT '是否已删除1删除 0正常',
    private String createDate;    // datetime NOT NULL COMMENT '创建日期',
    private String modifyDate;    // datetime NOT NULL COMMENT '修改日期',
    private String vendorName;    // varchar(32) DEFAULT NULL,
    private double discount;      // double(16,5) DEFAULT '1.00000' COMMENT '最低折扣',
    private double storeDiscount; // double(16,5) DEFAULT '1.00000' COMMENT '最低存储折扣',
    private int bindFlag;     // 1 已经绑定手机号
    private String storeName;     // 门店名称
    private String weChat; // 微信号
    private int position;       //职位 0请选择 1客服 2客服经理 3前台 4店长 5美肤师
    private String vendorCode;    // 商家编号
    private String password;//用户输入的密码
    private int belongStoreGroup;//用户是否属于店组 0:非店组用户;1:店组用户
    private List<String> storeGpCodeList;//店组编码
    private String userStoreCode;//店组用户所属门店编码
    private String userStoreName;//店组用户所属门店名称

    private User(Builder builder) {
        id = builder.id;
        vendorId = builder.vendorId;
        userType = builder.userType;
        userName = builder.userName;
        pwd = builder.pwd;
        realName = builder.realName;
        cellPhone = builder.cellPhone;
        email = builder.email;
        orgCode = builder.orgCode;
        storeCode = builder.storeCode;
        depCode = builder.depCode;
        memo = builder.memo;
        userStatus = builder.userStatus;
        df = builder.df;
        createDate = builder.createDate;
        modifyDate = builder.modifyDate;
        vendorName = builder.vendorName;
        discount = builder.discount;
        storeDiscount = builder.storeDiscount;
        vendorCode = builder.vendorCode;
        bindFlag = builder.bindFlag;
        password = builder.password;
    }

    public User() {
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", vendorId=" + vendorId +
                ", userType=" + userType +
                ", userName='" + userName + '\'' +
                ", pwd='" + pwd + '\'' +
                ", realName='" + realName + '\'' +
                ", cellPhone='" + cellPhone + '\'' +
                ", email='" + email + '\'' +
                ", orgCode='" + orgCode + '\'' +
                ", storeCode='" + storeCode + '\'' +
                ", depCode='" + depCode + '\'' +
                ", memo='" + memo + '\'' +
                ", userStatus=" + userStatus +
                ", df=" + df +
                ", createDate='" + createDate + '\'' +
                ", modifyDate='" + modifyDate + '\'' +
                ", vendorName='" + vendorName + '\'' +
                ", discount=" + discount +
                ", storeDiscount=" + storeDiscount +
                ", bindFlag=" + bindFlag +
                ", vendorCode='" + vendorCode + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getWeChat() {
        return weChat;
    }

    public void setWeChat(String weChat) {
        this.weChat = weChat;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getVendorId() {
        return vendorId;
    }

    public void setVendorId(long vendorId) {
        this.vendorId = vendorId;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getStoreCode() {
        return storeCode;
    }

    public void setStoreCode(String storeCode) {
        this.storeCode = storeCode;
    }

    public String getDepCode() {
        return depCode;
    }

    public void setDepCode(String depCode) {
        this.depCode = depCode;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public int getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(int userStatus) {
        this.userStatus = userStatus;
    }

    public int getDf() {
        return df;
    }

    public void setDf(int df) {
        this.df = df;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(String modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getStoreDiscount() {
        return storeDiscount;
    }

    public void setStoreDiscount(double storeDiscount) {
        this.storeDiscount = storeDiscount;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEmpty() {
        return TextUtils.isEmpty(userName);
    }

    public int getBindFlag() {
        return bindFlag;
    }

    public void setBindFlag(int bindFlag) {
        this.bindFlag = bindFlag;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public int getBelongStoreGroup() {
        return belongStoreGroup;
    }

    public void setBelongStoreGroup(int belongStoreGroup) {
        this.belongStoreGroup = belongStoreGroup;
    }

    public List<String> getStoreGpCodeList() {
        return storeGpCodeList;
    }

    public void setStoreGpCodeList(List<String> storeGpCodeList) {
        this.storeGpCodeList = storeGpCodeList;
    }

    public String getUserStoreCode() {
        return userStoreCode;
    }

    public void setUserStoreCode(String userStoreCode) {
        this.userStoreCode = userStoreCode;
    }

    public String getUserStoreName() {
        return userStoreName;
    }

    public void setUserStoreName(String userStoreName) {
        this.userStoreName = userStoreName;
    }

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeLong(this.id);
        out.writeLong(this.vendorId);
        out.writeInt(this.userType);
        out.writeObject(this.userName);
        out.writeObject(this.pwd);
        out.writeObject(this.realName);
        out.writeObject(this.cellPhone);
        out.writeObject(this.email);
        out.writeObject(this.orgCode);
        out.writeObject(this.storeCode);
        out.writeObject(this.depCode);
        out.writeObject(this.memo);
        out.writeInt(this.userStatus);
        out.writeInt(this.df);
        out.writeObject(this.createDate);
        out.writeObject(this.modifyDate);
        out.writeObject(this.vendorName);
        out.writeDouble(this.discount);
        out.writeDouble(this.storeDiscount);
        out.writeObject(this.vendorCode);
        out.writeObject(this.password);
        out.writeInt(this.bindFlag);
        out.writeInt(this.position);
        out.writeObject(this.weChat);
        out.writeObject(this.storeName);
        out.writeInt(this.belongStoreGroup);
        out.writeObject(this.userStoreCode);
        out.writeObject(this.userStoreName);
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.id = in.readLong();
        this.vendorId = in.readLong();
        this.userType = in.readInt();
        this.userName = (String) in.readObject();
        this.pwd = (String) in.readObject();
        this.realName = (String) in.readObject();
        this.cellPhone = (String) in.readObject();
        this.email = (String) in.readObject();
        this.orgCode = (String) in.readObject();
        this.storeCode = (String) in.readObject();
        this.depCode = (String) in.readObject();
        this.memo = (String) in.readObject();
        this.userStatus = in.readInt();
        this.df = in.readInt();
        this.createDate = (String) in.readObject();
        this.modifyDate = (String) in.readObject();
        this.vendorName = (String) in.readObject();
        this.discount = in.readDouble();
        this.storeDiscount = in.readDouble();
        this.vendorCode = (String) in.readObject();
        this.password = (String) in.readObject();
        try {
            this.bindFlag = in.readInt();
            this.position = in.readInt();
            this.weChat = (String) in.readObject();
            this.storeName = (String) in.readObject();
            this.belongStoreGroup = in.readInt();
            this.userStoreCode = (String) in.readObject();
            this.userStoreName = (String) in.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public static final class Builder {
        private long id;
        private long vendorId;
        private int userType;
        private String userName;
        private String pwd;
        private String realName;
        private String cellPhone;
        private String email;
        private String orgCode;
        private String storeCode;
        private String depCode;
        private String memo;
        private int userStatus;
        private int df;
        private String createDate;
        private String modifyDate;
        private String vendorName;
        private double discount;
        private double storeDiscount;
        private String vendorCode;
        private int bindFlag;
        private String password;

        public Builder() {
        }

        public Builder id(long val) {
            id = val;
            return this;
        }

        public Builder vendorId(long val) {
            vendorId = val;
            return this;
        }

        public Builder bindFlag(int val) {
            bindFlag = val;
            return this;
        }

        public Builder userType(int val) {
            userType = val;
            return this;
        }

        public Builder userName(String val) {
            userName = val;
            return this;
        }

        public Builder pwd(String val) {
            pwd = val;
            return this;
        }

        public Builder realName(String val) {
            realName = val;
            return this;
        }

        public Builder cellPhone(String val) {
            cellPhone = val;
            return this;
        }

        public Builder email(String val) {
            email = val;
            return this;
        }

        public Builder orgCode(String val) {
            orgCode = val;
            return this;
        }

        public Builder storeCode(String val) {
            storeCode = val;
            return this;
        }

        public Builder depCode(String val) {
            depCode = val;
            return this;
        }

        public Builder memo(String val) {
            memo = val;
            return this;
        }

        public Builder userStatus(int val) {
            userStatus = val;
            return this;
        }

        public Builder df(int val) {
            df = val;
            return this;
        }

        public Builder createDate(String val) {
            createDate = val;
            return this;
        }

        public Builder modifyDate(String val) {
            modifyDate = val;
            return this;
        }

        public Builder vendorName(String val) {
            vendorName = val;
            return this;
        }

        public Builder discount(double val) {
            discount = val;
            return this;
        }

        public Builder storeDiscount(double val) {
            storeDiscount = val;
            return this;
        }

        public Builder vendorCode(String val) {
            vendorCode = val;
            return this;
        }

        public Builder passwpord(String val) {
            password = val;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.vendorId);
        dest.writeInt(this.userType);
        dest.writeString(this.userName);
        dest.writeString(this.pwd);
        dest.writeString(this.realName);
        dest.writeString(this.cellPhone);
        dest.writeString(this.email);
        dest.writeString(this.orgCode);
        dest.writeString(this.storeCode);
        dest.writeString(this.depCode);
        dest.writeString(this.memo);
        dest.writeInt(this.userStatus);
        dest.writeInt(this.df);
        dest.writeString(this.createDate);
        dest.writeString(this.modifyDate);
        dest.writeString(this.vendorName);
        dest.writeDouble(this.discount);
        dest.writeDouble(this.storeDiscount);
        dest.writeInt(this.bindFlag);
        dest.writeString(this.storeName);
        dest.writeString(this.weChat);
        dest.writeInt(this.position);
        dest.writeString(this.vendorCode);
        dest.writeString(this.password);
        dest.writeInt(this.belongStoreGroup);
        dest.writeStringList(this.storeGpCodeList);
    }

    protected User(Parcel in) {
        this.id = in.readLong();
        this.vendorId = in.readLong();
        this.userType = in.readInt();
        this.userName = in.readString();
        this.pwd = in.readString();
        this.realName = in.readString();
        this.cellPhone = in.readString();
        this.email = in.readString();
        this.orgCode = in.readString();
        this.storeCode = in.readString();
        this.depCode = in.readString();
        this.memo = in.readString();
        this.userStatus = in.readInt();
        this.df = in.readInt();
        this.createDate = in.readString();
        this.modifyDate = in.readString();
        this.vendorName = in.readString();
        this.discount = in.readDouble();
        this.storeDiscount = in.readDouble();
        this.bindFlag = in.readInt();
        this.storeName = in.readString();
        this.weChat = in.readString();
        this.position = in.readInt();
        this.vendorCode = in.readString();
        this.password = in.readString();
        this.belongStoreGroup = in.readInt();
        this.storeGpCodeList = in.createStringArrayList();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
