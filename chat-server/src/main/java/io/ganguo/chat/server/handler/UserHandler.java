package io.ganguo.chat.server.handler;

import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.biz.service.UserService;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;
import io.ganguo.chat.core.transport.IMResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Tony
 * @createAt Feb 17, 2015
 */
@Component
public class UserHandler extends IMHandler {
    private Logger logger = LoggerFactory.getLogger(UserHandler.class);

    @Autowired
    private UserService userService;

    @Override
    public short getId() {
        return Handlers.USER;
    }

    @Override
    public void dispatch(IMConnection connection, IMRequest request) {
        Header header = request.getHeader();
        switch (header.getCommandId()) {
            case Commands.LOGIN_REQUEST:
                login(connection, request);
                break;

            default:
                break;
        }
    }

    private void login(IMConnection connection, IMRequest request) {
        User user = request.readEntity(User.class);
        boolean isSuccess = userService.login(user);

        IMResponse resp = new IMResponse();
        Header header = new Header();
        if (isSuccess) {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_SUCCESS);
            resp.setHeader(header);
            resp.writeEntity(userService.loadUser(user));
            resp.writeEntity(userService.getUserDetailById(user.getId()));
        } else {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_FAIL);
            resp.setHeader(header);
        }
        connection.sendResponse(resp);
    }

}
