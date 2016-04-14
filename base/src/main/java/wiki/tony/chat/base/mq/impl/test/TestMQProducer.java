package wiki.tony.chat.base.mq.impl.test;

import wiki.tony.chat.base.mq.MQMessage;
import wiki.tony.chat.base.mq.MQProducer;

/**
 * Created by Tony on 4/14/16.
 */
public class TestMQProducer implements MQProducer {

    @Override
    public MQMessage createMessage(String subject) {
        DefaultMQMessage message = new DefaultMQMessage();
        message.setSubject(subject);
        return message;
    }

    @Override
    public void sendMessage(MQMessage message) {
        TestMQProxy.instance().sendMessage(message);
    }
}
