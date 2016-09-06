package wiki.tony.chat.base.exception;

/**
 * 连接身份验证异常类
 */
public class ConnectionAuthException extends Exception {

    /**
     * 连接身份验证异常
     *
     * @param message 异常消息
     * @param cause   完整异常
     */
    public ConnectionAuthException(String message, Throwable cause) {
        super(message, cause);
    }

}
