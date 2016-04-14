package wiki.tony.chat.base.mq;

import java.util.Date;

/**
 * @author solosky created on 12/19/2015
 * @version $Id$
 */
public interface MQMessage {

    String getId();

    String getSubject();

    Date getCreateTime();

    byte[] getData();

    void setData(byte[] data);

}
