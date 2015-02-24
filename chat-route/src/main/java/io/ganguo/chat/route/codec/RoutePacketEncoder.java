package io.ganguo.chat.route.codec;

import io.ganguo.chat.route.bean.RouteResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class RoutePacketEncoder extends MessageToByteEncoder<RouteResponse> {

    @Override
    protected void encode(ChannelHandlerContext ctx, RouteResponse response, ByteBuf out) throws Exception {
        out.writeBytes(response.encode().getOrignalBuffer());
    }

}
