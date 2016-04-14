package wiki.tony.chat.base.mq.impl.test;

import wiki.tony.chat.base.mq.MQMessage;

import java.util.Date;

/**
 * @author solosky created on 12/19/2015
 * @version $Id$
 */
public class DefaultMQMessage implements MQMessage {
    private String id;
    private String subject;
    private byte[] data;
    private Date createTime = new Date();

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public String getSubject() {
        return this.subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public Date getCreateTime() {
        return this.createTime;
    }

    @Override
    public byte[] getData() {
        return this.data;
    }

    @Override
    public void setData(byte[] data) {
        this.data = data;
    }
}
