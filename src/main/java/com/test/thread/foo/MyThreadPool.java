package com.test.thread.foo;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.concurrent.*;

public class MyThreadPool {

    private static final ThreadFactory threadFactory = new ThreadFactoryBuilder()
            .setNameFormat("my-pool-%d").build();
    private static final ExecutorService threadPool = new ThreadPoolExecutor(
            5, 20, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
