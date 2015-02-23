package io.ganguo.chat.route.server;

import io.ganguo.chat.route.core.codec.PacketDecoder;
import io.ganguo.chat.route.core.codec.PacketEncoder;
import io.ganguo.chat.route.core.handler.IMHandler;
import io.ganguo.chat.route.core.handler.IMHandlerManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.context.ApplicationContext;

import java.util.Map;

public class ChatServerInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("decoder", new PacketDecoder(8192, 0, 4));
        pipeline.addLast("encoder", new PacketEncoder());

        pipeline.addLast("handler", new ChatServerHandler());

        initIMHandler();
    }

    private void initIMHandler() {
        ApplicationContext context = Bootstrap.getContext();

        // register all handlers
        Map<String, IMHandler> handlers = context.getBeansOfType(IMHandler.class);
        for (String key : handlers.keySet()) {
            IMHandler handler = handlers.get(key);
            IMHandlerManager.getInstance().register(handler);
        }
    }
}
