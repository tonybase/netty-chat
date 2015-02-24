package io.ganguo.chat.route.server.event;

import io.ganguo.chat.route.biz.bean.Presence;
import io.ganguo.chat.route.server.session.ClientSession;
import io.ganguo.chat.route.server.worker.PresenceWorker;

/**
 * Created by Tony on 2/22/15.
 */
public class PresenceEventDispatcher extends EventDispatcher<PresenceEventDispatcher.PresenceEventListener> {

    private final PresenceWorker presenceWorker = new PresenceWorker();

    public void availableSession(ClientSession session) {
        if (!listeners.isEmpty()) {
            for (PresenceEventListener listener : listeners) {
                listener.availableSession(session);
            }
        }
        presenceChanged(session);
    }

    public void unavailableSession(ClientSession session) {
        if (!listeners.isEmpty()) {
            for (PresenceEventListener listener : listeners) {
                listener.unavailableSession(session);
            }
        }
        presenceChanged(session);
    }

    public void presenceChanged(ClientSession session) {
        if (!listeners.isEmpty()) {
            for (PresenceEventListener listener : listeners) {
                listener.presenceChanged(session, session.getPresence());
            }
        }
        presenceWorker.push(session.getPresence());
    }

    public interface PresenceEventListener extends EventListener {
        public void availableSession(ClientSession session);

        public void unavailableSession(ClientSession session);

        public void presenceChanged(ClientSession session, Presence presence);

    }
}
