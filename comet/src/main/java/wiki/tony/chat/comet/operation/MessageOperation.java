package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.Message;
import wiki.tony.chat.base.service.MessageService;
import wiki.tony.chat.base.util.JsonUtils;
import wiki.tony.chat.comet.bean.Proto;

/**
 * Created by Tony on 4/14/16.
 */
@Component
public class MessageOperation extends AbstractOperation {

    private final Logger logger = LoggerFactory.getLogger(MessageOperation.class);

    public static final int OP = 2;
    public static final int OP_REPLY = 3;

    @Autowired
    private MessageService messageService;

    @Override
    public Integer op() {
        return OP;
    }

    @Override
    public void action(Channel ch, Proto proto) throws Exception {
        checkAuth(ch);

        Message msg = JsonUtils.fromJson(proto.getBody(), Message.class);
        msg.setFrom(getUserId(ch));
        messageService.push(msg);

        proto.setOperation(OP_REPLY);
        ch.writeAndFlush(proto);

    }

}
