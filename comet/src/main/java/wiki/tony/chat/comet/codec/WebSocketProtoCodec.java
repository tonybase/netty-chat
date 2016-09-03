package wiki.tony.chat.comet.codec;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.stereotype.Component;
import wiki.tony.chat.comet.bean.Proto;

import java.util.List;

/**
 * WebSocket 协议加解密
 */
@Component
@ChannelHandler.Sharable
public class WebSocketProtoCodec extends MessageToMessageCodec<TextWebSocketFrame, Proto> {

    private final ObjectMapper jsonMapper = new ObjectMapper();

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, Proto proto, List<Object> list) throws Exception {
        ObjectNode root = jsonMapper.createObjectNode();
        JsonNode body = jsonMapper.readTree(proto.getBody());
        root.put("ver", proto.getVersion());
        root.put("op", proto.getOperation());
        root.put("seq", proto.getSeqId());
        root.set("body", body);

        list.add(new TextWebSocketFrame(root.toString()));
    }

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, TextWebSocketFrame textWebSocketFrame, List<Object> list) throws Exception {
        String text = textWebSocketFrame.text();
        JsonNode root = jsonMapper.readTree(text);
        Proto proto = new Proto();
        if (root.has("ver")) {
            proto.setVersion(root.get("ver").shortValue());
        }
        if (root.has("op")) {
            proto.setOperation(root.get("op").asInt());
        }
        if (root.has("seq")) {
            proto.setSeqId(root.get("seq").asInt());
        }
        if (root.has("body")) {
            proto.setBody(root.get("body").toString().getBytes());
        }
        list.add(proto);
    }
}
