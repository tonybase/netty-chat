package io.ganguo.chat.route.client.handler;

import io.ganguo.chat.route.biz.entity.Message;
import io.ganguo.chat.core.connetion.IMConnection;
import io.ganguo.chat.core.handler.IMHandler;
import io.ganguo.chat.core.protocol.Commands;
import io.ganguo.chat.core.protocol.Handlers;
import io.ganguo.chat.core.transport.Header;
import io.ganguo.chat.core.transport.IMRequest;
import io.ganguo.chat.core.transport.IMResponse;
import io.ganguo.chat.route.server.dto.AckDTO;
import io.ganguo.chat.route.server.dto.MessageDTO;

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
            case Commands.USER_MESSAGE_SUCCESS:
                onUserMessageSuccess(connection, request);
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

        // 回执告诉对方已经收到，如果对方接收不到回执需要重复发送消息，客户端也需要对重复的消息做处理
        IMResponse resp = new IMResponse();
        Header header = new Header();
        header.setHandlerId(Handlers.MESSAGE);
        header.setCommandId(Commands.USER_MESSAGE_SUCCESS);
        resp.setHeader(header);
        resp.writeEntity(new AckDTO(message.getFrom(), message.getId()));
        connection.sendResponse(resp);
    }

    private void onUserMessageSuccess(IMConnection connection, IMRequest request) {
        AckDTO ack = request.readEntity(AckDTO.class);
        System.out.println("onMessageSuccess: " + ack);
    }
}
