package com.test.thread.foo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

public class Foo333 {

    // 使用Semaphore，为0时acquire()阻塞， release()时加1
    private Semaphore s1,s2;

    public Foo333() {
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
    }

    public void first(Runnable runnable) throws InterruptedException {
        runnable.run();
        s1.release();
    }

    public void second(Runnable runnable) throws InterruptedException {
        s1.acquire();
        runnable.run();
        s2.release();
    }

    public void three(Runnable runnable) throws InterruptedException {
        s2.acquire();
        runnable.run();
    }

    public static void main(String[] args) {
        Foo333 foo = new Foo333();
        // 创建线程池
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            try {
                foo.second(() -> System.out.println(Thread.currentThread().getName() + "---" + 2));
            } catch (InterruptedException e) {
            }
        });
        threadPool.execute(() -> {
            try {
                foo.three(() -> System.out.println(Thread.currentThread().getName() + "---" + 3));
            } catch (InterruptedException e) {
            }
        });
        threadPool.execute(() -> {
            try {
                foo.first(() -> System.out.println(Thread.currentThread().getName() + "---" + 1));
            } catch (InterruptedException e) {
            }
        });
        threadPool.shutdown();
    }
}
