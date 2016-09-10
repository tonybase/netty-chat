package wiki.tony.chat.logic.service;

import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import wiki.tony.chat.base.bean.Proto;
import wiki.tony.chat.base.pb.Message;
import wiki.tony.chat.base.service.MsgService;

/**
 * 消息服务
 * <p>
 * Created by Tony on 4/14/16.
 */
@Service("msgService")
public class MsgServiceImpl implements MsgService {

    private Logger logger = LoggerFactory.getLogger(MsgServiceImpl.class);

    @Override
    public boolean receive(Proto proto) {
        System.out.println("==receive===");
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
