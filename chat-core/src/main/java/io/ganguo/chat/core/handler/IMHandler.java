package io.ganguo.chat.core.handler;

import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.transport.IMRequest;

/**
 * Handler
 * 
 * @author Tony
 * @createAt Feb 17, 2015
 *
 */
public abstract class IMHandler {
	public abstract short getId();

	public abstract void dispatch(IMConnection connection, IMRequest request);
}
