package wiki.tony.chat.comet.operation;

import io.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import wiki.tony.chat.base.bean.AuthToken;
import wiki.tony.chat.base.bean.MQSubjectPrefix;
import wiki.tony.chat.base.mq.MQConsumer;
import wiki.tony.chat.base.mq.MQMessage;
import wiki.tony.chat.base.mq.MQMessageListener;
import wiki.tony.chat.base.mq.impl.test.TestMQConsumer;
import wiki.tony.chat.base.service.AuthService;
import wiki.tony.chat.base.util.JsonUtils;
import wiki.tony.chat.comet.bean.Constants;
import wiki.tony.chat.comet.bean.Proto;

/**
 * Created by Tony on 4/14/16.
 */
@Component
public class AuthOperation implements Operation {

    private final Logger logger = LoggerFactory.getLogger(AuthOperation.class);

    public static final int OP = 0;
    public static final int OP_REPLY = 1;

    @Value("${server.id}")
    private int serverId;
    @Autowired
    private AuthService authService;
    private MQConsumer mqConsumer = new TestMQConsumer();

    @Override
    public Integer op() {
        return OP;
    }

    @Override
    public void action(ChannelHandlerContext ctx, Proto proto) throws Exception {
        AuthToken auth = JsonUtils.fromJson(proto.getBody(), AuthToken.class);

        // check token
        Long userId = auth.getUserId();
        String token = auth.getToken();
        if (authService.auth(serverId, userId, token)) {
            // put user id
            ctx.attr(Constants.KEY_USER_ID).set(userId);
            // message consumer
            addConsumerListener(ctx, userId);
            logger.debug("auth ok");
        } else {
            logger.debug("auth fail");
        }

        proto.setOperation(OP_REPLY);
        ctx.writeAndFlush(proto);

    }

    private void addConsumerListener(final ChannelHandlerContext ctx, final Long userId) {
        mqConsumer.addListener(MQSubjectPrefix.MESSAGE, userId + "", new MQMessageListener() {
            @Override
            public void onMessage(MQMessage message) {
                Proto proto = new Proto();
                proto.setOperation(MessageOperation.OP);
                proto.setBody(message.getData());
                ctx.writeAndFlush(proto);

                logger.debug("consumer: {}", proto);
            }
        });
    }

}
