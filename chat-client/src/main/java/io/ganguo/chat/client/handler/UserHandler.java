package io.ganguo.chat.client.handler;

import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 *
 */
public class UserHandler extends IMHandler {

	private Logger logger = LoggerFactory.getLogger(UserHandler.class);

	@Override
	public short getId() {
		return Handlers.USER;
	}

	@Override
	public void dispatch(IMConnection connection, IMRequest request) {
		Header header = request.getHeader();
		switch (header.getCommandId()) {
		case Commands.LOGIN_SUCCESS:
			onLoginSuccess(connection, request);
			break;

		case Commands.LOGIN_FAIL:
			onLoginFail(connection, request);
			break;

		default:
			break;
		}
	}

	/**
	 * @param connection
	 * @param request
	 */
	private void onLoginSuccess(IMConnection connection, IMRequest request) {
		logger.info("onLoginSuccess");
	}

	/**
	 * @param connection
	 * @param request
	 */
	private void onLoginFail(IMConnection connection, IMRequest request) {
		logger.info("onLoginFail");
	}

}
