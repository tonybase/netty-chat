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
     * 写入对象
     *
     * @param buffer
     */
    public void writeData(DataBuffer buffer) {
        if (mData == null) {
            mData = new DataBuffer();
        }
        mData.writeDataBuffer(buffer);
    }

    /**
     * 写入对象
     *
     * @param IMSerializer
     */
    public void writeEntity(IMSerializer IMSerializer) {
        writeData(IMSerializer.encode(mHeader.getVersion()));
    }

    /**
     * Length | Header | Actual Content
     *
     * @return buffer
     */
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
            buffer.writeDataBuffer(mHeader.encode(mHeader.getVersion()));
            // data
            buffer.writeDataBuffer(mData);

            return buffer;
        } catch (Exception e) {
            logger.error("encode error!!!", e);
            throw new RuntimeException("encode error!!!");
        }
    }
}
