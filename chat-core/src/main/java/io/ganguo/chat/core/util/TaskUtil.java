package io.ganguo.chat.core.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务 Runnable Util
 * <p/>
 * Created by tony on 10/13/14.
 */
public class TaskUtil {

    private static ExecutorService singleExecutor = Executors.newSingleThreadExecutor();

    public static void single(Runnable runnable) {
        singleExecutor.submit(runnable);
    }

}
