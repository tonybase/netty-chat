package io.ganguo.chat.core.transport;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接收到数据请求
 * 
 * @author Tony
 * @createAt Feb 18, 2015
 *
 */
public class IMRequest {
	private Logger logger = LoggerFactory.getLogger(IMRequest.class);

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

	public void decode(DataBuffer buffer) {
		if (buffer != null) {
			try {
				// header
				mHeader = new Header();
				mHeader.decode(buffer);
				// data
				mData = buffer.readDataBuffer();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
}
