package io.ganguo.chat.route.server.event;

import io.ganguo.chat.route.biz.bean.Presence;
import io.ganguo.chat.route.server.session.ClientSession;

/**
 * Created by Tony on 2/22/15.
 */
public class PresenceEventDispatcher extends EventDispatcher<PresenceEventDispatcher.PresenceEventListener> {

    public void availableSession(ClientSession session) {
        if (!listeners.isEmpty()) {
            for (PresenceEventListener listener : listeners) {
                listener.availableSession(session);
            }
        }
    }

    public void unavailableSession(ClientSession session) {
        if (!listeners.isEmpty()) {
            for (PresenceEventListener listener : listeners) {
                listener.unavailableSession(session);
            }
        }
    }

    public interface PresenceEventListener extends EventListener {
        public void availableSession(ClientSession session);

        public void unavailableSession(ClientSession session);

        public void presenceChanged(ClientSession session, Presence presence);

    }
}
