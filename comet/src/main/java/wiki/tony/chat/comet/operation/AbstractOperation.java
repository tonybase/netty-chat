package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import wiki.tony.chat.base.bean.AuthToken;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.exception.NotAuthException;

/**
 * 操作类抽象方法
 */
public abstract class AbstractOperation implements Operation {

    protected String getKey(Channel ch) {
        return ch.attr(Constants.KEY_USER_ID).get();
    }

    protected void setKey(Channel ch, String key) {
        ch.attr(Constants.KEY_USER_ID).set(key);
    }

    protected void checkAuth(Channel ch) throws NotAuthException {
        if (!ch.hasAttr(Constants.KEY_USER_ID)) {
            throw new NotAuthException();
        }
    }

}
