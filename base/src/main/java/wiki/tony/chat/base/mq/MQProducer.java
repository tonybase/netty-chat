package wiki.tony.chat.base.mq;

/**
 * @author solosky created on 12/19/2015
 * @version $Id$
 */
public interface MQProducer {

    MQMessage createMessage(String topic);

    void sendMessage(MQMessage message);
    
}
