package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import wiki.tony.chat.base.bean.Proto;

/**
 * 操作类
 */
public interface Operation {

    Integer op();

    void action(Channel ch, Proto proto) throws Exception;

}
