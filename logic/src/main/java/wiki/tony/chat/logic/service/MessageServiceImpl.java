package wiki.tony.chat.logic.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.MQSubjectPrefix;
import wiki.tony.chat.base.bean.Message;
import wiki.tony.chat.base.mq.MQMessage;
import wiki.tony.chat.base.mq.MQProducer;
import wiki.tony.chat.base.mq.impl.test.TestMQProducer;
import wiki.tony.chat.base.service.MessageService;
import wiki.tony.chat.base.util.JsonUtils;

/**
 * 消息服务
 * <p>
 * Created by Tony on 4/14/16.
 */
@Component
public class MessageServiceImpl implements MessageService {

    private Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    private MQProducer mqProducer = new TestMQProducer();

    @Override
    public void push(Message msg) {
        logger.debug("producer:{} ", msg);

        MQMessage mqMessage = mqProducer.createMessage(MQSubjectPrefix.MESSAGE + msg.getTo());
        try {
            mqMessage.setData(JsonUtils.toJson(msg).getBytes());
            mqProducer.sendMessage(mqMessage);
        } catch (JsonProcessingException e) {
            logger.error("push error: {}", e);
        }
    }

}
