package io.ganguo.chat.biz.entity;


import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by Tony on 2/20/15.
 */
@Document
public class Message extends BaseEntity {
    // uin
    private long to;
    private long from;
    private byte type;
    private String message;
    private long createAt;

    public long getTo() {
        return to;
    }

    public void setTo(long to) {
        this.to = to;
    }

    public long getFrom() {
        return from;
    }

    public void setFrom(long from) {
        this.from = from;
    }

    public byte getType() {
        return type;
    }

    public void setType(byte type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(long createAt) {
        this.createAt = createAt;
    }

    @Override
    public String toString() {
        return "Message{" +
                "to=" + to +
                ", from=" + from +
                ", type=" + type +
                ", message='" + message + '\'' +
                ", createAt=" + createAt +
                '}';
    }

    public enum Type {
        SESSION_MSG(0), // 临时会话消息
        BUDDY_MSG(1),  // 好友消息
        GROUP_MSG(2);  // 群消息

        private byte mValue = 0;

        public byte getValue() {
            return mValue;
        }

        Type(int value) {
            mValue = (byte) value;
        }

        public static Type valueOfRaw(byte value) {
            for (Type type : Type.values()) {
                if (type.getValue() == value) {
                    return type;
                }
            }
            return SESSION_MSG;
        }
    }

}