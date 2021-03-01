package com.test.thread.foo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;

/**
 * 顺序打印，使用CyclicBarrier
 */
public class Foo4 {

    // await()必须执行容量的次数，才会统一执行
    private CyclicBarrier c1, c2;

    public Foo4() {
        c1 = new CyclicBarrier(2);
        c2 = new CyclicBarrier(2);
    }

    public void first(Runnable runnable) {
        try {
            runnable.run();
            c1.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }
    }

    public void second(Runnable runnable) {
        try {
            c1.await();
            runnable.run();
            c2.await();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }

    }

    public void three(Runnable runnable) {
        try {
            c2.await();
            runnable.run();
        } catch (InterruptedException e) {
        } catch (BrokenBarrierException e) {
        }
    }

    public static void main(String[] args) {
        Foo4 foo = new Foo4();
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> foo.second(() -> System.out.println(Thread.currentThread().getName() + "---" + 2)));
        threadPool.execute(() -> foo.three(() -> System.out.println(Thread.currentThread().getName() + "---" + 3)));
        threadPool.execute(() -> foo.first(() -> System.out.println(Thread.currentThread().getName() + "---" + 1)));
        threadPool.shutdown();
    }
 }
