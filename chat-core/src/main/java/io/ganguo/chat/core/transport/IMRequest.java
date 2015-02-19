package io.ganguo.chat.core.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收到数据请求
 *
 * @author Tony
 * @createAt Feb 18, 2015
 */
public class IMRequest {
    private Logger logger = LoggerFactory.getLogger(IMRequest.class);

    protected Header mHeader;
    protected DataBuffer mData;

    public IMRequest() {

    }

    public IMRequest(DataBuffer buffer) {
        decode(buffer);
    }

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

    public <T extends Entity> T readEntity(Class<T> entityClass) {
        try {
            T entity = entityClass.newInstance();
            readEntity(entity);
            return entity;
        } catch (Exception e) {
            logger.error("entity newInstance error!!!", e);
        }
        return null;
    }

    public <T extends Entity> void readEntity(T entity) {
        entity.decode(mData);
    }

    public void decode(DataBuffer buffer) {
        if (buffer != null) {
            try {
                // length
                int length = buffer.readInt();
                // header
                mHeader = new Header();
                mHeader.setLength(length);
                mHeader.decode(buffer);
                // data
                mData = buffer.readDataBuffer();
            } catch (Exception e) {
                logger.error(e.getMessage());
            }
        }
    }
}
