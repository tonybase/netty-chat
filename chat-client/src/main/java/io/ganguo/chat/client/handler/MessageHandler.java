package io.ganguo.chat.client.handler;

import io.ganguo.chat.biz.entity.Message;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;
import io.ganguo.chat.server.dto.MessageDTO;

/**
 * Created by Tony on 2/20/15.
 */
public class MessageHandler extends IMHandler {

    @Override
    public short getId() {
        return Handlers.MESSAGE;
    }

    @Override
    public void dispatch(IMConnection connection, IMRequest request) {
        Header header = request.getHeader();
        switch (header.getCommandId()) {
            case Commands.USER_MESSAGE_REQUEST:
                receiveMessage(connection, request);
                break;
            case Commands.ERROR_USER_NOT_EXISTS:
                System.out.println("用户不存在接收不到消息～～");
                break;
            default:
                connection.close();
                break;
        }
    }

    private void receiveMessage(IMConnection connection, IMRequest request) {
        MessageDTO messageDTO = request.readEntity(MessageDTO.class);
        Message message = messageDTO.getMessage();

        System.out.println("message: " + message);
    }

}
