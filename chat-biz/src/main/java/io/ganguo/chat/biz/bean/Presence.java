package io.ganguo.chat.biz.bean;

public enum Presence {

    UNAVAILABLE(0), AVAILABLE(1);

    private byte mValue = 0;

    public byte getValue() {
        return mValue;
    }

    Presence(int value) {
        mValue = (byte) value;
    }

    public static Presence valueOfRaw(byte value) {
        for (Presence presence : Presence.values()) {
            if (presence.getValue() == value) {
                return presence;
            }
        }
        return UNAVAILABLE;
    }
}
