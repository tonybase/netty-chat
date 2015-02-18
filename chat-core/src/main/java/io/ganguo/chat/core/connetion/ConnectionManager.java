package io.ganguo.chat.core.connetion;

import io.netty.channel.ChannelHandlerContext;
import io.netty.util.AttributeKey;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 长连接管理器
 * 
 * @author Tony
 * @createAt Feb 17, 2015
 *
 */
public class ConnectionManager {

	private static final AttributeKey<IMConnection> CONN_KEY = AttributeKey.valueOf("connection");
	private static ConnectionManager mInstance = null;

	private AtomicInteger mUniqueGenerator = null;
	private Map<Integer, IMConnection> mConnections = null;

	private ConnectionManager() {
		mUniqueGenerator = new AtomicInteger(0);
		mConnections = new ConcurrentHashMap<Integer, IMConnection>();
	}

	public static ConnectionManager getInstance() {
		if (mInstance == null) {
			mInstance = new ConnectionManager();
		}
		return mInstance;
	}

	public IMConnection create(ChannelHandlerContext ctx) {
		final IMConnection conn = new IMConnection(mUniqueGenerator.incrementAndGet(), ctx);
		ctx.attr(CONN_KEY).set(conn);
		mConnections.put(conn.getId(), conn);
		return conn;
	}

	public void add(IMConnection c) {
		mConnections.put(c.getId(), c);
	}

	public IMConnection get(int id) {
		return mConnections.get(id);
	}

	public IMConnection find(ChannelHandlerContext ctx) {
		return ctx.attr(CONN_KEY).get();
	}

	public void remove(int key) {
		mConnections.remove(key);
	}

	public void remove(IMConnection c) {
		if (c != null) {
			mConnections.remove(c.getId());
		}
	}

	public void remove(ChannelHandlerContext ctx) {
		remove(ctx.attr(CONN_KEY).get());
	}

	public int count() {
		return mConnections.size();
	}

}
