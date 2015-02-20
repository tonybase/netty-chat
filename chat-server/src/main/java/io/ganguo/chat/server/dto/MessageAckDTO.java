package io.ganguo.chat.server.dto;

import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.IMSerializer;

/**
 * Created by Tony on 2/20/15.
 */
public class MessageAckDTO implements IMSerializer {

    private int code;
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer buffer = new DataBuffer();
        buffer.writeInt(code);
        buffer.writeString(message);
        return buffer;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        code = buffer.readInt();
        message = buffer.readString();
    }
}
