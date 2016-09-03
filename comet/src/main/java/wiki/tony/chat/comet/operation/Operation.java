package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import wiki.tony.chat.comet.bean.Proto;

/**
 * 操作类
 */
public interface Operation {

    Integer op();

    void action(Channel ctx, Proto proto) throws Exception;

}
