package io.ganguo.chat.core.transport;


/**
 * @author Tony
 * @createAt Feb 18, 2015
 *
 */
public abstract class Entity {

	private short version = version();

	public short getVersion() {
		return version;
	}

	public void setVersion(short version) {
		this.version = version;
	}

	/**
	 * 实体类版本
	 */
	public abstract short version();

	/**
	 * 把Request数据结构编码成一个DataBuffer
	 */
	public abstract DataBuffer encode();

	/**
	 * 把DataBuffer解包构造一个Response对象，getResponse函数必须在调用完decode函数之后才能得到真实的包
	 */
	public abstract void decode(DataBuffer data);
}
