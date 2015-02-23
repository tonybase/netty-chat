package io.ganguo.chat.route.client;

import io.ganguo.chat.route.biz.bean.ClientType;
import io.ganguo.chat.route.biz.entity.User;
import io.ganguo.chat.route.core.protocol.Handlers;
import io.ganguo.chat.route.core.protocol.Commands;
import io.ganguo.chat.route.core.transport.Header;
import io.ganguo.chat.route.core.transport.IMResponse;
import io.ganguo.chat.route.server.dto.UserDTO;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 */
public class ChatClient {

    private final String host;
    private final int port;

    public ChatClient() {
        host = "127.0.0.1";
        port = 9090;
    }

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void run() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChatClientInitializer());

            ChannelFuture future = bootstrap.connect(host, port);
            // awaitUninterruptibly() 等待连接成功
            Channel channel = future.awaitUninterruptibly().channel();
            login(channel);

//            future.channel().closeFuture().awaitUninterruptibly();
        } finally {
//            group.shutdownGracefully();
        }
    }

    public void login(Channel channel) {
        User user = new User();
        user.setClientType(ClientType.MAC.value());
        user.setAccount("test2");
        user.setPassword("test2");

        IMResponse resp = new IMResponse();
        Header header = new Header();
        header.setHandlerId(Handlers.USER);
        header.setCommandId(Commands.LOGIN_REQUEST);
        resp.setHeader(header);
        resp.writeEntity(new UserDTO(user));

        channel.writeAndFlush(resp).awaitUninterruptibly();
    }

    public static void main(String[] args) throws Exception {
//        for (int i = 0; i < 100; i++) {
        new ChatClient().run();
//            Thread.sleep(10);
//        }
    }
}
