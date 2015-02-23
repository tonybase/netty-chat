package io.ganguo.chat.route.server;

import io.ganguo.chat.route.core.connetion.ConnectionManager;
import io.ganguo.chat.route.core.connetion.IMConnection;
import io.ganguo.chat.route.core.handler.IMHandler;
import io.ganguo.chat.route.core.handler.IMHandlerManager;
import io.ganguo.chat.route.core.transport.Header;
import io.ganguo.chat.route.core.transport.IMRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatServerHandler extends SimpleChannelInboundHandler<IMRequest> {

    private Logger logger = LoggerFactory.getLogger(ChatServerHandler.class);

    private final ConnectionManager mConnectionManager = ConnectionManager.getInstance();

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        super.handlerAdded(ctx);

        mConnectionManager.create(ctx);

        logger.info("handlerAdded " + mConnectionManager.count());
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        super.handlerRemoved(ctx);

        mConnectionManager.remove(ctx);

        logger.info("handlerRemoved " + mConnectionManager.count());
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, IMRequest request) throws Exception {
        logger.info("messageReceived header:" + request.getHeader());

        IMConnection conn = mConnectionManager.find(ctx);
        Header header = request.getHeader();

        IMHandler handler = IMHandlerManager.getInstance().find(header.getHandlerId());
        if (handler != null) {
            handler.dispatch(conn, request);
        } else {
            logger.warn("Not found handlerId: " + header.getHandlerId());
        }
    }
}
