package io.ganguo.chat.route.server.event;

/**
 * Created by Tony on 2/22/15.
 */
public class EventDispatcherManager {

    private static PresenceEventDispatcher mPresenceEventDispatcher = null;

    public static PresenceEventDispatcher getPresenceEventDispatcher() {
        if (mPresenceEventDispatcher == null) {
            mPresenceEventDispatcher = new PresenceEventDispatcher();
        }
        return mPresenceEventDispatcher;
    }

}
