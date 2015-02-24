package io.ganguo.chat.route;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.springframework.stereotype.Component;

/**
 * Created by Tony on 2/22/15.
 */
@Component
public class ChatRouteServer {

    private final int PORT;

    public ChatRouteServer() {
        PORT = 10006;
    }

    public ChatRouteServer(int port) {
        PORT = port;
    }

    /**
     * netty
     *
     * @throws Exception
     */
    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();

        try {
            ServerBootstrap bootstrap = new ServerBootstrap()
                    .group(bossGroup, workGroup)
                    .channel(NioServerSocketChannel.class)
                    .childHandler(new ChatRouteServerInitializer());

            bootstrap
                    .bind(PORT)
                    .sync().channel()
                    .closeFuture().sync();
        } finally {
            bossGroup.shutdownGracefully();
            workGroup.shutdownGracefully();
        }
    }

}
