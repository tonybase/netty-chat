package wiki.tony.chat.comet.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import wiki.tony.chat.base.bean.AuthToken;
import wiki.tony.chat.base.bean.Message;
import wiki.tony.chat.base.util.JsonUtils;
import wiki.tony.chat.comet.bean.Proto;
import wiki.tony.chat.comet.codec.TcpProtoCodec;

/**
 * Created by Tony on 4/14/16.
 */
public class ChatClient {
    private final String host;
    private final int port;

    public ChatClient(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();

        try {
            Bootstrap b = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(host, port)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        protected void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new TcpProtoCodec());
                        }
                    });

            ChannelFuture f = b.connect().sync();

            AuthToken auth = new AuthToken();
            auth.setUserId(2);
            auth.setToken("test2");
            Proto proto = new Proto();
            proto.setVersion((short) 1);
            proto.setOperation(0);
            proto.setBody(JsonUtils.toJson(auth).getBytes());
            f.channel().writeAndFlush(proto);

            Message msg = new Message();
            msg.setTo(1L);
            msg.setContent("hello1");
            proto = new Proto();
            proto.setVersion((short) 1);
            proto.setOperation(2);
            proto.setBody(JsonUtils.toJson(msg).getBytes());
            f.channel().writeAndFlush(proto);

            f.channel().closeFuture().sync();
        } finally {
            group.shutdownGracefully().sync();
        }
    }

    public static void main(String[] args) throws Exception {
        new ChatClient("127.0.0.1", 9090).start();
    }
}
