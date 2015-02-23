package io.ganguo.chat.route.biz.entity;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigInteger;

@Document
public class User extends BaseEntity {
    @Indexed(unique = true)
    private long uin;
    @Indexed(unique = true)
    private String account;
    private String password;
    private String avatarUrl;
    private String nickName;
    private byte gender;
    private byte clientType;

    private BigInteger userDetailId;

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public byte getGender() {
        return gender;
    }

    public void setGender(byte gender) {
        this.gender = gender;
    }

    public byte getClientType() {
        return clientType;
    }

    public void setClientType(byte clientType) {
        this.clientType = clientType;
    }

    public BigInteger getUserDetailId() {
        return userDetailId;
    }

    public void setUserDetailId(BigInteger userDetailId) {
        this.userDetailId = userDetailId;
    }

    @Override
    public String toString() {
        return "User{" +
                "uin=" + uin +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                ", nickName='" + nickName + '\'' +
                ", gender=" + gender +
                ", userDetailId=" + userDetailId +
                '}';
    }
}
