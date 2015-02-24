package io.ganguo.chat.route.server.worker;

import io.ganguo.chat.route.biz.bean.Presence;
import io.ganguo.chat.route.core.protocol.Commands;
import io.ganguo.chat.route.core.protocol.Handlers;
import io.ganguo.chat.route.core.transport.Header;
import io.ganguo.chat.route.core.transport.IMResponse;
import io.ganguo.chat.route.core.util.TaskUtil;
import io.ganguo.chat.route.server.dto.PresenceDTO;
import io.ganguo.chat.route.server.session.ClientSession;
import io.ganguo.chat.route.server.session.ClientSessionManager;

import java.util.Map;

/**
 * Created by Tony on 2/24/15.
 */
public class PresenceWorker extends IMWorker<Presence> {

    public static final int PROCESS_DELAYER_MILLIS = 20;

    public PresenceWorker() {
        TaskUtil.pool(this);
    }

    @Override
    public void process(Presence presence) {
        // broadcast
        Map<Long, ClientSession> sessions = ClientSessionManager.getInstance().sessions();
        for (long uin : sessions.keySet()) {
            if (presence.getUin() != uin) {
                ClientSession session = sessions.get(uin);

                IMResponse resp = new IMResponse();
                Header header = new Header();
                header.setHandlerId(Handlers.USER);
                header.setCommandId(Commands.USER_PRESENCE_CHANGED);
                resp.setHeader(header);
                resp.writeEntity(new PresenceDTO(presence));
                session.getConnection().sendResponse(resp);
            }
        }
        // 处理完当前server connection，还需要处理在其它server的用户
        // 把未发送的uin分发到route服务

        try {
            Thread.sleep(PROCESS_DELAYER_MILLIS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
