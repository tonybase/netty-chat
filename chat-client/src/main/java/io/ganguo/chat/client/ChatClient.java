package io.ganguo.chat.client;

import io.ganguo.chat.biz.bean.ClientType;
import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMResponse;
import io.ganguo.chat.server.dto.UserDTO;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.stereotype.Component;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 */
@Component
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
            Channel channel = future.sync().channel();
            login(channel);

        } finally {
//            group.shutdownGracefully();
        }
    }

    public void login(Channel channel) {
        User user = new User();
        user.setClientType(ClientType.MAC.getValue());
        user.setAccount("test1");
        user.setPassword("test1");

        IMResponse resp = new IMResponse();
        Header header = new Header();
        header.setHandlerId(Handlers.USER);
        header.setCommandId(Commands.LOGIN_REQUEST);
        resp.setHeader(header);
        resp.writeEntity(new UserDTO(user));

        channel.writeAndFlush(resp);
    }

}
