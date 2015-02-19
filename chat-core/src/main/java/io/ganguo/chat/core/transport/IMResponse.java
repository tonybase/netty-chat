package io.ganguo.chat.core.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应答，发送数据
 *
 * @author Tony
 * @createAt Feb 18, 2015
 */
public class IMResponse {
    private Logger logger = LoggerFactory.getLogger(IMResponse.class);

    protected Header mHeader;
    protected DataBuffer mData;

    public Header getHeader() {
        return mHeader;
    }

    public void setHeader(Header header) {
        mHeader = header;
    }

    public DataBuffer getData() {
        return mData;
    }

    public void setData(DataBuffer data) {
        mData = data;
    }

    /**
     * append data
     *
     * @param data
     */
    public void writeData(DataBuffer data) {
        if (mData == null) {
            mData = new DataBuffer();
        }
        mData.writeDataBuffer(data);
    }

    public void writeEntity(Entity entity) {
        writeData(entity.encode());
    }

    public DataBuffer encode() {
        try {

            DataBuffer buffer = new DataBuffer();
            // length
            int length = Header.PROTOCOL_HEADER_LENGTH;
            if (mData != null) {
                length += mData.readableBytes();
            }
            buffer.writeInt(length);
            // header
            mHeader.setLength(length);
            buffer.writeDataBuffer(mHeader.encode());
            // data
            buffer.writeDataBuffer(mData);

            return buffer;
        } catch (Exception e) {
            logger.error("encode error!!!", e);
            throw new RuntimeException("encode error!!!");
        }
    }
}
