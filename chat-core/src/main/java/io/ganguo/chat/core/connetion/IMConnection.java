package io.ganguo.chat.core.connetion;

import io.ganguo.chat.core.transport.IMResponse;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;

/**
 * 连接封装类，可以对连接发送数据
 *
 * @author Tony
 * @createAt Feb 17, 2015
 */
public class IMConnection {
    private Integer mId;
    private ChannelHandlerContext mContext;
    private volatile boolean isKilled = false;

    public IMConnection(int id, ChannelHandlerContext ctx) {
        mId = id;
        mContext = ctx;
    }

    public Integer getId() {
        return mId;
    }

    public Channel getChannel() {
        return mContext.channel();
    }

    public boolean isAvailable() {
        return mContext.channel().isActive();
    }

    public boolean isKilled() {
        return isKilled;
    }

    public void kill() {
        if (!isKilled) {
            isKilled = true;
            mContext.channel().close();
        }
    }

    public void sendResponse(IMResponse resp) {
        mContext.writeAndFlush(resp);
    }

}
