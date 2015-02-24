package io.ganguo.chat.route.server.dto;

import io.ganguo.chat.route.biz.bean.Presence;
import io.ganguo.chat.route.core.transport.DataBuffer;
import io.ganguo.chat.route.core.transport.IMSerializer;

/**
 * Created by Tony on 2/24/15.
 */
public class PresenceDTO implements IMSerializer {

    private Presence presence;

    public PresenceDTO() {
    }

    public PresenceDTO(Presence presence) {
        this.presence = presence;
    }

    public Presence getPresence() {
        return presence;
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    @Override
    public DataBuffer encode(short version) {
        DataBuffer buffer = new DataBuffer();
        buffer.writeLong(presence.getUin());
        buffer.writeByte(presence.getMode());
        buffer.writeString(presence.getStatus());
        return buffer;
    }

    @Override
    public void decode(DataBuffer buffer, short version) {
        presence = new Presence();
        presence.setUin(buffer.readLong());
        presence.setMode(buffer.readByte());
        presence.setStatus(buffer.readString());
    }
}
