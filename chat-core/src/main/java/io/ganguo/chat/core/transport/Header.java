package io.ganguo.chat.core.transport;

import io.ganguo.chat.core.transport.Entity;

/**
 * TCP协议的头文件
 */
public class Header extends Entity {
    public static final int PROTOCOL_HEADER_LENGTH = 8;

    private int length = PROTOCOL_HEADER_LENGTH; // 数据包长度，包括包头长度

    private short handlerId; // SID
    private short commandId; // CID
    private short reserved; // 保留，可用于如序列号等

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public short getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(short handlerId) {
        this.handlerId = handlerId;
    }

    public short getCommandId() {
        return commandId;
    }

    public void setCommandId(short commandId) {
        this.commandId = commandId;
    }

    public short getReserved() {
        return reserved;
    }

    public void setReserved(short reserved) {
        this.reserved = reserved;
    }

    @Override
    public short version() {
        return 0;
    }

    @Override
    public DataBuffer encode() {
        DataBuffer data = new DataBuffer(PROTOCOL_HEADER_LENGTH);
        data.writeShort(version());
        data.writeShort(handlerId);
        data.writeShort(commandId);
        data.writeShort(reserved);
        return data;
    }

    @Override
    public void decode(DataBuffer data) {
        setVersion(data.readShort());
        if (getVersion() == version()) {
            handlerId = data.readShort();
            commandId = data.readShort();
            reserved = data.readShort();
        }
    }

    @Override
    public String toString() {
        return "Header [length=" + length + ", handlerId=" + handlerId + ", commandId=" + commandId + ", reserved=" + reserved + "]";
    }

}
