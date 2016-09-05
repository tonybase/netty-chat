package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.service.MsgService;
import wiki.tony.chat.base.bean.Proto;

/**
 * 消息操作
 */
@Component
public class MessageOperation extends AbstractOperation {

    private final Logger logger = LoggerFactory.getLogger(MessageOperation.class);

    public static final int OP = 2;
    public static final int OP_REPLY = 3;

    @Autowired
    private MsgService messageService;

    @Override
    public Integer op() {
        return OP;
    }

    @Override
    public void action(Channel ch, Proto proto) throws Exception {
        checkAuth(ch);

        // receive a message
        messageService.receive(proto);

        // write message reply
        proto.setOperation(OP_REPLY);
        proto.setBody(null);
        ch.writeAndFlush(proto);
    }

}
