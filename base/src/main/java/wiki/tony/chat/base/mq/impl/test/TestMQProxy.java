package wiki.tony.chat.base.mq.impl.test;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wiki.tony.chat.base.mq.MQMessage;
import wiki.tony.chat.base.mq.MQMessageListener;

import java.util.Map;

/**
 * Created by Tony on 4/14/16.
 */
public class TestMQProxy {

    private static final Logger LOG = LoggerFactory.getLogger(TestMQProxy.class);
    private static final TestMQProxy instance = new TestMQProxy();

    private Map<String, MQMessageListener> consumerListener = Maps.newConcurrentMap();

    public static TestMQProxy instance() {
        return instance;
    }

    public void sendMessage(MQMessage message) {
        MQMessageListener listener = consumerListener.get(message.getTopic());
        if (listener != null) {
            listener.onMessage(message);
        } else {
            LOG.warn("not found MQMessageListener: {}", message);
        }
    }

    public void addListener(String subjectPrefix, String consumeGroup, MQMessageListener messageListener) {
        consumerListener.put(subjectPrefix + consumeGroup, messageListener);
    }

}
