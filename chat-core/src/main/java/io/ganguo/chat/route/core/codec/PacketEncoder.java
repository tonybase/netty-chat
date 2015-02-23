package io.ganguo.chat.route.core.codec;

import io.ganguo.chat.route.core.transport.IMResponse;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class PacketEncoder extends MessageToByteEncoder<IMResponse> {

	@Override
	protected void encode(ChannelHandlerContext ctx, IMResponse response, ByteBuf out) throws Exception {
		out.writeBytes(response.encode().getOrignalBuffer());
	}

}
