package wiki.tony.chat.comet.exception;

/**
 * 聊天异常类
 */
public abstract class ChatException extends Exception {

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }

}
