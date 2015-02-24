package io.ganguo.chat.core.transport;


/**
 * @author Tony
 * @createAt Feb 18, 2015
 */
public interface IMSerializer {

    /**
     * 把Request数据结构编码成一个DataBuffer
     */
    public DataBuffer encode(short version);

    /**
     * 把DataBuffer解包构造一个Response对象，getResponse函数必须在调用完decode函数之后才能得到真实的包
     */
    public void decode(DataBuffer buffer, short version);

}
