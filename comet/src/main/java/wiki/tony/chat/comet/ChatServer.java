package wiki.tony.chat.comet;

/**
 * Created by Tony on 4/13/16.
 */
public interface ChatServer {

    void start() throws Exception;

    void restart() throws Exception;

    void shutdown();

}
