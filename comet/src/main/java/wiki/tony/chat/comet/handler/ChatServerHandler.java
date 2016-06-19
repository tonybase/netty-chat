package wiki.tony.chat.comet.handler;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.AuthToken;
import wiki.tony.chat.base.service.AuthService;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.bean.Proto;
import wiki.tony.chat.comet.ChatOperation;
import wiki.tony.chat.comet.operation.Operation;

/**
 * netty handler
 * <p>
 * Created by Tony on 4/13/16.
 */
@Component
@ChannelHandler.Sharable
public class ChatServerHandler extends SimpleChannelInboundHandler<Proto> {

    private static final Logger LOG = LoggerFactory.getLogger(ChatServerHandler.class);

    @Autowired
    private ChatOperation chatOperation;
    @Autowired
    private AuthService authService;
    @Value("${server.id}")
    private int serverId;

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Proto proto) throws Exception {
        Operation op = chatOperation.find(proto.getOperation());
        // execute operation
        if (op != null) {
            LOG.debug(proto.toString());
            op.action(ctx.channel(), proto);
        } else {
            LOG.warn("Not found operationId: " + proto.getOperation());
        }
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        AuthToken authToken = ctx.attr(Constants.KEY_USER_TOKEN).get();
        if (authToken != null) {
            authService.quit(serverId, authToken);
        }
        LOG.debug("handlerRemoved: {}", authToken);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.error("exceptionCaught", cause);
    }
}
