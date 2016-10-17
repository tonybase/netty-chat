package wiki.tony.chat.comet.handler;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import wiki.tony.chat.base.bean.Proto;
import io.netty.channel.ChannelHandler.Sharable;
import wiki.tony.chat.base.service.AuthService;
import wiki.tony.chat.base.service.MsgService;
import wiki.tony.chat.comet.ChatOperation;
import wiki.tony.chat.comet.operation.Operation;

/**
 * 消息处理类
 */
@Sharable
@Service()
@Scope("prototype")
public class ChatServerHandler extends SimpleChannelInboundHandler<Proto> {

    private static final Logger LOG = LoggerFactory.getLogger(ChatServerHandler.class);

    @Autowired
    private ChatOperation chatOperation;
    @Autowired
    private AuthService authService;
    @Autowired
    private MsgService msgService;
    @Value("${server.id}")
    private int serverId;

    /**
     * 客户端连接
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        // 添加
        LOG.info("客户端与服务端连接开启");
    }

    /**
     * 客户端关闭
     *
     * @param ctx 上下文
     * @throws Exception 异常
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        // 移除
        ctx.close();
        LOG.info("客户端与服务端连接关闭");
    }

    /**
     * 读取消息
     *
     * @param ctx   通道上下文
     * @param proto 协议
     * @throws Exception 异常
     */
    @Override
    protected void messageReceived(ChannelHandlerContext ctx, Proto proto) throws Exception {
        LOG.info("收到消息");
        Operation op = chatOperation.find(proto.getOperation());
        if (op != null) {
            LOG.debug(proto.toString());
            op.action(ctx.channel(), proto);
            msgService.receive(proto);
        } else {
            LOG.warn("Not found operationId: " + proto.getOperation());
        }
    }

    /**
     * 异常消息
     *
     * @param ctx   通道上下文
     * @param cause 线程
     * @throws Exception 异常
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOG.error("异常消息", cause);
        ctx.close();
    }

}
