package io.ganguo.chat.biz.bean;

/**
 * @author Tony
 * @createAt Feb 18, 2015
 */
public enum Gender {

    MALE(1), FEMALE(2), UNKNOUWN(0);

    private byte mValue = 0;

    public byte getValue() {
        return mValue;
    }

    Gender(int value) {
        mValue = (byte) value;
    }

    public static Gender valueOfRaw(byte value) {
        for (Gender gender : Gender.values()) {
            if (gender.getValue() == value) {
                return gender;
            }
        }
        return UNKNOUWN;
    }
}
