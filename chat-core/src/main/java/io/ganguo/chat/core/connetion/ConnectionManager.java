package io.ganguo.chat.core.connetion;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

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

    private static final AttributeKey<IMConnection> CONN_KEY = AttributeKey.valueOf("connection");
    private static ConnectionManager mInstance = null;

    private AtomicLong mUniqueGenerator = null;
    private Map<Long, IMConnection> mConnections = null;

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
        // 取负，如果用户登录建立连接，将会使用uin替换，为正数
        final IMConnection conn = new IMConnection(-mUniqueGenerator.incrementAndGet(), ctx);
        ctx.attr(CONN_KEY).set(conn);
        mConnections.put(conn.getUin(), conn);
        return conn;
    }

    public void bindUin(Long uin, IMConnection conn) {
        remove(conn);

        conn.setUin(uin);
        add(conn);
    }

    public void add(IMConnection c) {
        mConnections.put(c.getUin(), c);
    }

    public IMConnection get(Long id) {
        return mConnections.get(id);
    }

    public IMConnection find(ChannelHandlerContext ctx) {
        return ctx.attr(CONN_KEY).get();
    }

    public void remove(Long uin) {
        mConnections.remove(uin);
    }

    public void remove(IMConnection conn) {
        if (conn != null) {
            remove(conn.getUin());
        }
    }

    public void remove(ChannelHandlerContext ctx) {
        remove(find(ctx));
    }

    public int count() {
        return mConnections.size();
    }

}
