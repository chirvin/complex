package com.test.thread.foo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * 按序打印，使用Semaphore，Semaphore可以释放，CountDownLatch不可以。
 */
public class Foo3 {
    //声明两个 Semaphore变量
    private Semaphore spa,spb;
    public Foo3() {
        // 初始化Semaphore为0的原因：如果这个Semaphore为零，
        // 如果另一线程调用(acquire)时Semaphore就会产生阻塞，便可以控制second和third线程的执行
        spa = new Semaphore(0);
        spb = new Semaphore(0);
    }
    public void first(Runnable printFirst) throws InterruptedException {
        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        //只有等first线程释放Semaphore后使Semaphore值为1,另外一个线程才可以调用（acquire）
        spa.release();
    }
    public void second(Runnable printSecond) throws InterruptedException {
        //只有spa为1才能执行acquire，如果为0就会产生阻塞
        spa.acquire();
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        spb.release();
    }
    public void third(Runnable printThird) throws InterruptedException {
        //只有spb为1才能通过，如果为0就会阻塞
        spb.acquire();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Foo3 foo = new Foo3();
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
                foo.third(() -> System.out.println(Thread.currentThread().getName() + "---" + 3));
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
