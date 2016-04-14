package wiki.tony.chat.comet.operation;

import io.netty.channel.ChannelHandlerContext;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.exception.NotAuthException;

/**
 * Created by Tony on 4/14/16.
 */
public abstract class AbstractOperation implements Operation {

    protected Long getUserId(ChannelHandlerContext ctx) {
        return ctx.attr(Constants.KEY_USER_ID).get();
    }

    protected void checkAuth(ChannelHandlerContext ctx) throws NotAuthException {
        if (getUserId(ctx) == null) {
            throw new NotAuthException();
        }
    }

}
