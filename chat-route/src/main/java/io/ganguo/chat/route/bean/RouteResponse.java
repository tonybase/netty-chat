package io.ganguo.chat.route.bean;

import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.IMResponse;

/**
 * Created by Tony on 2/24/15.
 */
public class RouteResponse extends IMResponse {
    private DataBuffer mRawData;

    public RouteResponse(DataBuffer mRawData) {
        this.mRawData = mRawData;
    }

    public DataBuffer getRawData() {
        return mRawData;
    }

    public void setRawData(DataBuffer mRawData) {
        this.mRawData = mRawData;
    }

    @Override
    public DataBuffer encode() {
        return mRawData;
    }
}
