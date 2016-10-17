package wiki.tony.chat.comet.operation;

import io.netty.channel.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.Constants;
import wiki.tony.chat.base.service.MsgService;
import wiki.tony.chat.base.bean.Proto;

/**
 * 消息操作
 */
@Component
public class MessageOperation extends AbstractOperation {

    private final Logger logger = LoggerFactory.getLogger(MessageOperation.class);

    @Autowired
    private MsgService msgService;

    @Override
    public Integer op() {
        return Constants.OP_MESSAGE;
    }

    @Override
    public void action(Channel ch, Proto proto) throws Exception {
        checkAuth(ch);

        // receive a message
        msgService.receive(proto);

        // write message reply
        proto.setOperation(Constants.OP_MESSAGE_REPLY);
        proto.setBody(null);
        ch.writeAndFlush(proto);
    }

}
