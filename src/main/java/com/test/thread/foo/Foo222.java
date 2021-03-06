package com.test.thread.foo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

public class Foo222 {

    // countDown()方法减1，减为0时await()才会执行
    private CountDownLatch c1,c2;

    public Foo222() {
        c1 = new CountDownLatch(1);
        c2 = new CountDownLatch(1);
    }

    public void first(Runnable runnable) {
        runnable.run();
        c1.countDown();
    }

    public void second(Runnable runnable) throws InterruptedException {
        c1.await();
        runnable.run();
        c2.countDown();
    }

    public void three(Runnable runnable) throws InterruptedException {
        c2.await();
        runnable.run();
    }

    public static void main(String[] args) {
        Foo222 foo = new Foo222();
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
        threadPool.execute(() -> foo.first(() -> System.out.println(Thread.currentThread().getName() + "---" + 1)));
        threadPool.shutdown();
    }
}
