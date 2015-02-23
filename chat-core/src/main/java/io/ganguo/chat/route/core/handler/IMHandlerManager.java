package io.ganguo.chat.route.core.handler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 *
 */
public class IMHandlerManager {

	private Map<Short, IMHandler> mHandlers = null;
	private static IMHandlerManager mInstance = null;

	private IMHandlerManager() {
		mHandlers = new HashMap<Short, IMHandler>();
	}

	public static IMHandlerManager getInstance() {
		if (mInstance == null) {
			mInstance = new IMHandlerManager();
		}
		return mInstance;
	}

	public IMHandler find(Short handlerId) {
		return mHandlers.get(handlerId);
	}

	public void register(Class<?> clazz) {
		try {
			IMHandler handler = (IMHandler) clazz.newInstance();
			mHandlers.put(handler.getId(), handler);
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public void register(IMHandler handler) {
		mHandlers.put(handler.getId(), handler);
	}
}
