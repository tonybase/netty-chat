package wiki.tony.chat.comet.exception;

/**
 * Created by Tony on 4/14/16.
 */
public abstract class ChatException extends Exception {

    public ChatException(String message) {
        super(message);
    }

    public ChatException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
