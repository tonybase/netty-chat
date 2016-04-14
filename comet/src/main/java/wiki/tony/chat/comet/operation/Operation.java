package wiki.tony.chat.comet.operation;

import io.netty.channel.ChannelHandlerContext;
import wiki.tony.chat.comet.bean.Proto;

/**
 * Created by Tony on 4/14/16.
 */
public interface Operation {

    Integer op();

    void action(ChannelHandlerContext ctx, Proto proto) throws Exception;

}
