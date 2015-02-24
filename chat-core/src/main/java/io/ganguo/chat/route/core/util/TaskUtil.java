package io.ganguo.chat.route.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务 Runnable Util
 * <p/>
 * Created by tony on 10/13/14.
 */
public class TaskUtil {

    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();
    private static ExecutorService poolExecutor = Executors.newCachedThreadPool();

    public static void single(Runnable runnable) {
        singleExecutor.submit(runnable);
    }

    public static void pool(Runnable runnable) {
        poolExecutor.submit(runnable);
    }
}
