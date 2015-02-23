package io.ganguo.chat.route.server.dto;

import io.ganguo.chat.route.core.transport.DataBuffer;
import io.ganguo.chat.route.core.transport.IMSerializer;

/**
 * Created by Tony on 2/20/15.
 */
public class AckDTO implements IMSerializer {

    private long to;
    private String ackId;

    public AckDTO() {
    }

    public AckDTO(long to, String ackId) {
        this.to = to;
        this.ackId = ackId;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public String getAckId() {
        return ackId;
    }

    public void setAckId(String ackId) {
        this.ackId = ackId;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer buffer = new DataBuffer();
        buffer.writeLong(to);
        buffer.writeString(ackId);
        return buffer;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        to = buffer.readLong();
        ackId = buffer.readString();
    }

    @Override
    public String toString() {
        return "AckDTO{" +
                "to=" + to +
                ", ackId='" + ackId + '\'' +
                '}';
    }
}
