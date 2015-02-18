package io.ganguo.chat.biz.bean;

/**
 * @author Tony
 * @createAt Feb 18, 2015
 *
 */
public enum Gender {

	MALE(1), FEMALE(0), UNKNOUWN(-1);

	private byte mValue = 0;

	public byte getValue() {
		return mValue;
	}

	Gender(int value) {
		mValue = (byte) value;
	};

	public static Gender valueOfRaw(byte value) {
		switch (value) {
		case 0:
			return FEMALE;
		case 1:
			return MALE;
		default:
			return UNKNOUWN;
		}
	}
}
