package io.ganguo.chat.route;

import io.ganguo.chat.route.codec.RoutePacketDecoder;
import io.ganguo.chat.route.codec.RoutePacketEncoder;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.handler.IMHandlerManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

import java.util.Map;

public class ChatRouteServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("decoder", new RoutePacketDecoder(8192, 0, 4));
        pipeline.addLast("encoder", new RoutePacketEncoder());

        pipeline.addLast("handler", new ChatRouteServerHandler());
       
        initIMHandler();
    }

    private void initIMHandler() {
        // register all handlers
        Map<String, IMHandler> handlers = ChatContext.getBeansOfType(IMHandler.class);
        for (String key : handlers.keySet()) {
            IMHandler handler = handlers.get(key);
            IMHandlerManager.getInstance().register(handler);
        }
    }
}
