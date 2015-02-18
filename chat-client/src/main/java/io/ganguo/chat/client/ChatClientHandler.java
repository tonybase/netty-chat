package io.ganguo.chat.client;

import io.ganguo.chat.core.connetion.ConnectionManager;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.handler.IMHandlerManager;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ChatClientHandler extends SimpleChannelInboundHandler<IMRequest> {
	private Logger logger = LoggerFactory.getLogger(ChatClientHandler.class);

	private final ConnectionManager mConnectionManager = ConnectionManager.getInstance();

	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		logger.info("handlerAdded");

		mConnectionManager.create(ctx);
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		logger.info("handlerRemoved");

		mConnectionManager.remove(ctx);
	}

	@Override
	protected void messageReceived(ChannelHandlerContext ctx, IMRequest request) throws Exception {
		logger.info("messageReceived");

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
