package io.ganguo.chat.server;

import io.ganguo.chat.core.codec.PacketDecoder;
import io.ganguo.chat.core.codec.PacketEncoder;
import io.ganguo.chat.core.handler.IMHandlerManager;
import io.ganguo.chat.server.handler.UserHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.context.ApplicationContext;

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

        IMHandlerManager.getInstance().register(context.getBean(UserHandler.class));
    }
}
