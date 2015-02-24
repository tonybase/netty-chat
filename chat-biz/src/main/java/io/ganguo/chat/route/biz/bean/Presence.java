package io.ganguo.chat.route.biz.bean;

/**
 * Created by Tony on 2/21/15.
 */
public class Presence {
    private long uin;
    private byte mode = Mode.AVAILABLE.value();
    private long activeTime = System.currentTimeMillis();
    private String status;

    public long getUin() {
        return uin;
    }

    public void setUin(long uin) {
        this.uin = uin;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public void setActiveTime(long activeTime) {
        this.activeTime = activeTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte getMode() {
        return mode;
    }

    public void setMode(byte mode) {
        this.mode = mode;
    }

    /**
     * An enum to represent the presence mode.
     */
    public enum Mode {
        /**
         * Unavailable(the default)
         */
        UNAVAILABLE(-1),

        /**
         * Available
         */
        AVAILABLE(0),


        /**
         * Work
         */
        WORK(1),

        /**
         * Do not disturb.
         */
        DND(2),

        /**
         * Away.
         */
        AWAY(3);

        private byte mValue = 0;

        public byte value() {
            return mValue;
        }

        Mode(int value) {
            mValue = (byte) value;
        }

        public static Mode valueOfRaw(byte value) {
            for (Mode v : Mode.values()) {
                if (v.value() == value) {
                    return v;
                }
            }
            return UNAVAILABLE;
        }

    }
}
