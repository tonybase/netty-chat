package io.ganguo.chat.biz.entity;

import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.Entity;

/**
 * @author Tony
 * @createAt Feb 18, 2015
 *
 */
public class UserDetail extends Entity {
	private long id;
	private String email;
	private String telphone;
	private String birthDate;
	private String city;
	private String province;
	private String position;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
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
		data.writeString(email);
		data.writeString(telphone);
		data.writeString(birthDate);
		data.writeString(city);
		data.writeString(province);
		data.writeString(position);
		return null;
	}

	@Override
	public void decode(DataBuffer data) {
		setVersion(data.readShort());
		if (getVersion() == version()) {
			id = data.readLong();
			email = data.readString();
			telphone = data.readString();
			birthDate = data.readString();
			city = data.readString();
			province = data.readString();
			position = data.readString();
		}
	}

}
