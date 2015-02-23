package io.ganguo.chat.route.client;

import io.ganguo.chat.route.client.handler.MessageHandler;
import io.ganguo.chat.route.client.handler.UserHandler;
import io.ganguo.chat.route.core.codec.PacketDecoder;
import io.ganguo.chat.route.core.codec.PacketEncoder;
import io.ganguo.chat.route.core.handler.IMHandlerManager;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class ChatClientInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        ChannelPipeline pipeline = channel.pipeline();

        pipeline.addLast("decoder", new PacketDecoder(8192, 0, 4));
        pipeline.addLast("encoder", new PacketEncoder());

        pipeline.addLast("handler", new ChatClientHandler());

        initIMHandler();
    }

    private void initIMHandler() {
        IMHandlerManager.getInstance().register(UserHandler.class);
        IMHandlerManager.getInstance().register(MessageHandler.class);
    }
}
