package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.exception.NotAuthException;

/**
 * Created by Tony on 4/14/16.
 */
public abstract class AbstractOperation implements Operation {

    protected Long getUserId(Channel ch) {
        return ch.attr(Constants.KEY_USER_ID).get();
    }

    protected void setUserId(Channel ch, Long userId) {
        ch.attr(Constants.KEY_USER_ID).set(userId);
    }

    protected void checkAuth(Channel ch) throws NotAuthException {
        if (getUserId(ch) == null) {
            throw new NotAuthException();
        }
    }

}
