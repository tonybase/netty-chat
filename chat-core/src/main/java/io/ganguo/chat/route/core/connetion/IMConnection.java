package io.ganguo.chat.route.core.connetion;

import io.ganguo.chat.route.core.transport.IMResponse;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

/**
 * 连接封装类，可以对连接发送数据
 *
 * @author Tony
 * @createAt Feb 17, 2015
 */
public class IMConnection {
    public static final AttributeKey<Long> ATTR_CONN_ID = AttributeKey.valueOf("connection_id");
    public static final AttributeKey<Long> ATTR_CONN_UIN = AttributeKey.valueOf("connection_uin");

    private long mId;
    private volatile boolean isClosed = false;
    private ChannelHandlerContext mContext;
    private ConnectionCloseListener closeListener;

    public IMConnection(Long id, ChannelHandlerContext ctx) {
        mId = id;
        mContext = ctx;
    }

    public long getId() {
        return mId;
    }

    public long getUin() {
        return mContext.attr(IMConnection.ATTR_CONN_UIN).get();
    }

    public void setUin(long mUIN) {
        mContext.attr(IMConnection.ATTR_CONN_UIN).set(mUIN);
    }

    public boolean validate() {
        if (isClosed()) {
            return false;
        }
        // 发送一个心跳包，同步等待success
        return true;
    }

    public boolean isActive() {
        return mContext != null && mContext.channel().isActive();
    }

    public boolean isClosed() {
        return isClosed || !isActive();
    }

    public void close() {
        if (!isClosed) {
            mContext.channel().close();
            notifyRemoved();
        }
    }

    public void sendResponse(IMResponse resp) {
        if (isActive()) {
            mContext.writeAndFlush(resp);
        }
    }

    public void notifyRemoved() {
        if (closeListener != null) {
            closeListener.onClosed(this);
        }
        isClosed = true;
        mContext = null;
        closeListener = null;
    }

    public void registerCloseListener(ConnectionCloseListener listener) {
        if (closeListener != null) {
            throw new IllegalStateException("Close listener already configured");
        }
        if (isClosed()) {
            listener.onClosed(this);
        } else {
            closeListener = listener;
        }
    }

    public void removeCloseListener(ConnectionCloseListener listener) {
        if (closeListener == listener) {
            closeListener = null;
        }
    }

    public interface ConnectionCloseListener {
        public void onClosed(IMConnection connection);
    }

}
