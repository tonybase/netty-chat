package wiki.tony.chat.base.mq;

/**
 * @author solosky created on 12/19/2015
 * @version $Id$
 */
public interface MQMessageListener {
    void onMessage(MQMessage message);
}
