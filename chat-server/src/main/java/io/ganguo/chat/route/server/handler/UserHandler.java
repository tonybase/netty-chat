package io.ganguo.chat.route.server.handler;

import io.ganguo.chat.route.biz.entity.Login;
import io.ganguo.chat.route.biz.entity.User;
import io.ganguo.chat.route.biz.service.impl.UserServiceImpl;
import io.ganguo.chat.route.core.connetion.IMConnection;
import io.ganguo.chat.route.core.handler.IMHandler;
import io.ganguo.chat.route.core.protocol.Commands;
import io.ganguo.chat.route.core.protocol.Handlers;
import io.ganguo.chat.route.core.transport.Header;
import io.ganguo.chat.route.core.transport.IMRequest;
import io.ganguo.chat.route.core.transport.IMResponse;
import io.ganguo.chat.route.server.dto.LoginDTO;
import io.ganguo.chat.route.server.dto.UserDTO;
import io.ganguo.chat.route.server.session.ClientSession;
import io.ganguo.chat.route.server.session.ClientSessionManager;
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
    private UserServiceImpl userService;

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
            case Commands.LOGIN_CHANNEL_REQUEST:
                loginChannel(connection, request);
                break;
            default:
                connection.close();
                break;
        }
    }

    private void loginChannel(IMConnection connection, IMRequest request) {
        LoginDTO loginDTO = request.readEntity(LoginDTO.class);
        Login login = loginDTO.getLogin();

        boolean isSuccess = userService.authenticate(login.getUin(), login.getAuthToken());
        IMResponse resp = new IMResponse();
        Header header = request.getHeader();
        if (isSuccess) {
            User user = userService.findByUin(login.getUin());

            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_CHANNEL_SUCCESS);
            resp.setHeader(header);
            resp.writeEntity(new UserDTO(user));
            connection.sendResponse(resp);

            // 是否已经登录进来了，踢下线
            ClientSession old = ClientSessionManager.getInstance().get(login.getUin());
            if (old != null && old.getConnection() != connection) {
                kick(old.getConnection(), request);
            }
            // 绑定用户UIN到connection中
            ClientSession session = new ClientSession(login, connection);
            ClientSessionManager.getInstance().add(session);
        } else {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_CHANNEL_FAIL);
            resp.setHeader(header);
            connection.sendResponse(resp);
            connection.close();
        }
    }

    private void login(IMConnection connection, IMRequest request) {
        UserDTO userDTO = request.readEntity(UserDTO.class);
        String account = userDTO.getUser().getAccount();
        String password = userDTO.getUser().getPassword();
        Login login = userService.login(account, password);

        IMResponse resp = new IMResponse();
        Header header = request.getHeader();
        if (login != null) {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_SUCCESS);
            resp.setHeader(header);
            resp.writeEntity(new LoginDTO(login));
            connection.sendResponse(resp);

            // 是否已经登录进来了，踢下线
            ClientSession old = ClientSessionManager.getInstance().get(login.getUin());
            if (old != null && old.getConnection() != connection) {
                kick(old.getConnection(), request);
            }
            // 绑定用户UIN到connection中
            ClientSessionManager.getInstance().add(new ClientSession(login, connection));
        } else {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_FAIL);
            resp.setHeader(header);
            connection.sendResponse(resp);
            connection.close();
        }
    }

    /**
     * 被踢下线
     *
     * @param connection
     */
    private void kick(IMConnection connection, IMRequest request) {
        // send 离线信息，并kill
        IMResponse resp = new IMResponse();
        Header header = request.getHeader();
        header.setHandlerId(getId());
        header.setCommandId(Commands.LOGIN_CHANNEL_KICKED);
        resp.setHeader(header);
        connection.sendResponse(resp);
        connection.close();
    }

}
