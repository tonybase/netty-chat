package wiki.tony.chat.base.bean;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户验证类
 */
public class AuthToken {

    @JsonProperty("user_id")
    private long userId;
    @JsonProperty("token")
    private String token;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "AuthToken{" +
                "userId=" + userId +
                ", token=" + token +
                '}';
    }
}
