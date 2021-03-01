package com.test.thread.foo;

import java.util.concurrent.ExecutorService;

/**
 * 按序打印，使用Synchronized
 */
public class Foo1 {

    private static int count = 0;
    private static Object lock = new Object();

    public Foo1() {

    }
    public static void first(Runnable printFirst) throws InterruptedException {
        synchronized(lock){
            while(count % 3 != 0) {
                lock.wait();
            }
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            count++;
            lock.notifyAll();
        }
    }
    public static void second(Runnable printSecond) throws InterruptedException {
        synchronized(lock){
            while(count % 3 != 1) {
                lock.wait();
            }
            // printSecond.run() outputs "second". Do not change or remove this line.
            printSecond.run();
            count++;
            lock.notifyAll();
        }
    }
    public static void third(Runnable printThird) throws InterruptedException {
        synchronized(lock){
            while(count % 3 != 2) {
                lock.wait();
            }
            // printThird.run() outputs "third". Do not change or remove this line.
            printThird.run();
            count++;
            lock.notifyAll();
        }
    }

    public static void main(String[] args) {

        Foo1 foo = new Foo1();

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
                e.printStackTrace();
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
