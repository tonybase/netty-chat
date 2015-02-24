package io.ganguo.chat.route.bean;

import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.IMSerializer;

/**
 * Created by Tony on 2/24/15.
 */
public class RouteData implements IMSerializer {
    
    private short type; // user/room
    private long to;    // to user_uin / room_code
    private DataBuffer data;

    public RouteData(DataBuffer dataBuffer) {
        decode(dataBuffer, (short) 0);
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public DataBuffer getData() {
        return data;
    }

    public void setData(DataBuffer data) {
        this.data = data;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer buffer = new DataBuffer();
        buffer.writeShort(type);
        buffer.writeLong(to);
        buffer.writeDataBuffer(data);
        return buffer;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        type = buffer.readShort();
        to = buffer.readLong();
        data = buffer.readDataBuffer();
    }
}
