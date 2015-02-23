package io.ganguo.chat.route.server.event;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by Tony on 2/22/15.
 */
public abstract class EventDispatcher<T> {

    protected List<T> listeners = new CopyOnWriteArrayList<T>();

    /**
     * Registers a listener to receive events.
     *
     * @param listener the listener.
     */
    public void addListener(T listener) {
        if (listener == null) {
            throw new NullPointerException();
        }
        listeners.add(listener);
    }

    /**
     * Unregisters a listener to receive events.
     *
     * @param listener the listener.
     */
    public void removeListener(T listener) {
        listeners.remove(listener);
    }

}
