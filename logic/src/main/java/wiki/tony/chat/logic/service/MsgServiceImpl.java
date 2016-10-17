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
        logger.debug("producer:{} ", proto);
        switch (proto.getOperation()) {
            case 1:
                logger.info("认证消息");
                break;
            case 2:
                logger.info("认证回复");
                break;
            case 3:
                logger.info("心跳消息");
                break;
            case 4:
                logger.info("心跳回复");
                break;
            case 5:
                logger.info("发送消息");
                break;
            case 6:
                logger.info("回复消息");
                break;
        }
//        Message.MsgData data;
//        try {
//            data = Message.MsgData.parseFrom(proto.getBody());
//        } catch (InvalidProtocolBufferException e) {
//            logger.error("invalid proto {} {}", proto, e.getMessage());
//        }
        // TODO
        return true;
    }

}
