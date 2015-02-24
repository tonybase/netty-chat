package io.ganguo.chat.route.server.worker;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by Tony on 2/24/15.
 */
public abstract class IMWorker<T> implements Runnable {

    private boolean isWorking = true;
    private Queue<T> workingQueue = new ConcurrentLinkedQueue<T>();

    public void push(T t) {
        synchronized (workingQueue) {
            workingQueue.add(t);
            workingQueue.notifyAll();
        }
    }

    public boolean isDone() {
        return workingQueue.isEmpty();
    }

    @Override
    public void run() {
        while (isWorking) {
            synchronized (workingQueue) {
                if (isDone()) {
                    try {
                        workingQueue.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            process(workingQueue.poll());
        }
    }

    public abstract void process(T t);
}
