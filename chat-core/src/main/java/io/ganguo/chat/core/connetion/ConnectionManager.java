package io.ganguo.chat.core.connetion;

import io.netty.channel.ChannelHandlerContext;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 长连接管理器
 *
 * @author Tony
 * @createAt Feb 17, 2015
 */
public class ConnectionManager {

    private static ConnectionManager mInstance = null;

    private final AtomicLong mUniqueGenerator;
    private final Map<Long, IMConnection> mConnections;

    private ConnectionManager() {
        mUniqueGenerator = new AtomicLong(0);
        mConnections = new ConcurrentHashMap<Long, IMConnection>();
    }

    public static ConnectionManager getInstance() {
        if (mInstance == null) {
            mInstance = new ConnectionManager();
        }
        return mInstance;
    }

    public IMConnection create(ChannelHandlerContext ctx) {
        long id = mUniqueGenerator.incrementAndGet();
        IMConnection conn = new IMConnection(id, ctx);
        mConnections.put(id, conn);
        // put id to channel
        ctx.attr(IMConnection.ATTR_CONN_ID).set(id);
        return conn;
    }

    public void remove(long id) {
        IMConnection conn = mConnections.remove(id);
        if (conn != null) {
            conn.notifyRemoved();
        }
    }

    public void remove(IMConnection conn) {
        if (conn != null) {
            remove(conn.getId());
        }
    }

    public void remove(ChannelHandlerContext ctx) {
        remove(find(ctx));
    }

    public IMConnection get(long id) {
        return mConnections.get(id);
    }

    public IMConnection find(ChannelHandlerContext ctx) {
        return get(ctx.attr(IMConnection.ATTR_CONN_ID).get());
    }

    /**
     * mark
     * 不要使用Map里面的size()，每次获取都需要计算
     *
     * @return
     */
    public int count() {
        return mConnections.size();
    }
}
