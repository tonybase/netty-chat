package wiki.tony.chat.base.mq.impl.test;

import wiki.tony.chat.base.mq.MQConsumer;
import wiki.tony.chat.base.mq.MQMessageListener;

/**
 * Created by Tony on 4/14/16.
 */
public class TestMQConsumer implements MQConsumer {

    @Override
    public void addListener(String subjectPrefix, String consumeGroup, MQMessageListener messageListener) {
        TestMQProxy.instance().addListener(subjectPrefix, consumeGroup, messageListener);
    }

}
