package wiki.tony.chat.logic.service;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.Proto;
import wiki.tony.chat.base.pb.Message;
import wiki.tony.chat.base.service.MessageService;

/**
 * 消息服务
 * <p>
 * Created by Tony on 4/14/16.
 */
@Component
public class MessageServiceImpl implements MessageService {

    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Override
    public boolean receive(Proto proto) {
        logger.debug("producer:{} ", proto);

        Message.MsgData data;
        try {
            data = Message.MsgData.parseFrom(proto.getBody());
        } catch (InvalidProtocolBufferException e) {
            logger.error("invalid proto {} {}", proto, e.getMessage());
        }
        // TODO
        return true;
    }

}
