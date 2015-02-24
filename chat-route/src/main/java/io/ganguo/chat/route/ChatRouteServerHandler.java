package io.ganguo.chat.route;

import io.ganguo.chat.core.connetion.ConnectionManager;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.handler.IMHandlerManager;
import io.ganguo.chat.route.bean.RouteData;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatRouteServerHandler extends SimpleChannelInboundHandler<RouteData> {

    private Logger logger = LoggerFactory.getLogger(ChatRouteServerHandler.class);

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
    protected void messageReceived(ChannelHandlerContext ctx, RouteData routeData) throws Exception {
        logger.info("messageReceived header:" + routeData.getType());

        IMConnection conn = mConnectionManager.find(ctx);
        IMHandler<RouteData> handler = IMHandlerManager.getInstance().find(routeData.getType());
        if (handler != null) {
            handler.dispatch(conn, routeData);
        } else {
            logger.warn("Not found handlerId: " + routeData.getType());
        }
    }
}
