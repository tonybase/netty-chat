package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import wiki.tony.chat.base.bean.AuthToken;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.exception.NotAuthException;

/**
 * Created by Tony on 4/14/16.
 */
public abstract class AbstractOperation implements Operation {

    protected AuthToken getAuthToken(Channel ch) {
        return ch.attr(Constants.KEY_USER_TOKEN).get();
    }

    protected void setAuthToken(Channel ch, AuthToken token) {
        ch.attr(Constants.KEY_USER_TOKEN).set(token);
    }

    protected void checkAuth(Channel ch) throws NotAuthException {
        if (getAuthToken(ch) == null) {
            throw new NotAuthException();
        }
    }

}
