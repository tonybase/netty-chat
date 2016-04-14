package wiki.tony.chat.comet.exception;

/**
 * Created by Tony on 4/14/16.
 */
public class NotAuthException extends ChatException {
    public NotAuthException() {
        super("未登录认证");
    }
}
