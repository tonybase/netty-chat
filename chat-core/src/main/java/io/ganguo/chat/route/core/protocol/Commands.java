package io.ganguo.chat.route.core.protocol;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 */
public class Commands {
    /**
     * 登录
     */
    public static final short LOGIN_REQUEST = 0x0001;
    public static final short LOGIN_SUCCESS = 0x1001;
    public static final short LOGIN_FAIL = 0x1000;
    /**
     * 登录 Channel
     */
    public static final short LOGIN_CHANNEL_REQUEST = 0x0002;
    public static final short LOGIN_CHANNEL_SUCCESS = 0x2002;
    public static final short LOGIN_CHANNEL_FAIL = 0x2000;
    public static final short LOGIN_CHANNEL_KICKED = 0x2001;

    /**
     * 消息
     */
    public static final short USER_MESSAGE_REQUEST = 0x0001;
    public static final short USER_MESSAGE_SUCCESS = 0x1001;
    public static final short USER_MESSAGE_FAIL = 0x1000;
    public static final short ERROR_USER_NOT_EXISTS = 0x1002;

}
