package io.ganguo.chat.client;

import io.ganguo.chat.biz.bean.Presence;
import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMResponse;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
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
                    .handler(new ChatClientInitailizer());

            Channel channel = bootstrap.connect(host, port).sync().channel();
            login(channel);
        } finally {
            // group.shutdownGracefully();
        }
    }

    public void login(Channel channel) {
        User user = new User();
        user.setAccount("test");
        user.setPassword("test");
        user.setPresence(Presence.AVAILABLE);

        IMResponse resp = new IMResponse();
        Header header = new Header();
        header.setHandlerId(Handlers.USER);
        header.setCommandId(Commands.LOGIN_REQUEST);
        resp.setHeader(header);
        resp.writeEntity(user);

        channel.writeAndFlush(resp);
    }

    public static void main(String[] args) throws Exception {
        // for (int i = 0; i < 100000; i++) {
        new ChatClient("localhost", 9090).run();
        // }
    }
}
