package io.ganguo.chat.server.handler;

import io.ganguo.chat.biz.bean.ClientType;
import io.ganguo.chat.biz.entity.Login;
import io.ganguo.chat.biz.entity.User;
import io.ganguo.chat.biz.service.impl.UserServiceImpl;
import io.ganguo.chat.core.connetion.ConnectionManager;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.DataBuffer;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;
import io.ganguo.chat.core.transport.IMResponse;
import io.ganguo.chat.server.dto.LoginDTO;
import io.ganguo.chat.server.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.rmi.runtime.Log;

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
                connection.kill();
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

            // 绑定用户UIN到connection中
            ConnectionManager.getInstance().bindUin(user.getUin(), connection);
        } else {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_CHANNEL_FAIL);
            resp.setHeader(header);
            connection.sendResponse(resp);
            connection.kill();
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

            // 绑定用户UIN到connection中
            ConnectionManager.getInstance().bindUin(login.getUin(), connection);
        } else {
            header.setHandlerId(getId());
            header.setCommandId(Commands.LOGIN_FAIL);
            resp.setHeader(header);
            connection.sendResponse(resp);
            connection.kill();
        }
    }

}
