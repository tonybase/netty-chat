package wiki.tony.chat.comet.initializer;

import io.netty.channel.ChannelInitializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.tony.chat.comet.codec.TcpProtoCodec;
import wiki.tony.chat.comet.handler.ChatServerHandler;

/**
 * TCP服务初始化类
 */
@Component
public class TcpServerInitializer extends ChannelInitializer {

    @Autowired
    private TcpProtoCodec protoCodec;
    @Autowired
    private ChatServerHandler serverHandler;

    @Override
    protected void initChannel(io.netty.channel.Channel ch) throws Exception {
        ch.pipeline().addLast(protoCodec);
        ch.pipeline().addLast(serverHandler);
    }

}
