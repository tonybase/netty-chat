package io.ganguo.chat.biz.entity;

import io.ganguo.chat.biz.bean.Gender;
import io.ganguo.chat.biz.bean.Presence;
import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.Entity;

public class User extends Entity {
	private long id;
	private String account;
	private String password;
	private String authToken;
	private String avatarUrl;
	private String nickName;
	private Gender gender = Gender.UNKNOUWN;
	private Presence presence = Presence.UNAVAILABLE;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthToken() {
		return authToken;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public Gender getGender() {
		return gender;
	}

	public void setGender(Gender gender) {
		this.gender = gender;
	}

	public Presence getPresence() {
		return presence;
	}

	public void setPresence(Presence presence) {
		this.presence = presence;
	}

	@Override
	public short version() {
		return 0;
	}

	@Override
	public DataBuffer encode() {
		DataBuffer data = new DataBuffer();
		data.writeShort(version());
		data.writeLong(id);
		data.writeString(account);
		data.writeString(password);
		data.writeString(authToken);
		data.writeString(nickName);
		data.writeString(avatarUrl);
		data.writeByte(gender.getValue());
		data.writeByte(presence.getValue());
		return data;
	}

	@Override
	public void decode(DataBuffer data) {
		setVersion(data.readShort());
		if (getVersion() == version()) {
			id = data.readLong();
			account = data.readString();
			password = data.readString();
			authToken = data.readString();
			nickName = data.readString();
			avatarUrl = data.readString();
			gender = Gender.valueOfRaw(data.readByte());
			presence = Presence.valueOfRaw(data.readByte());
		}
	}

}
