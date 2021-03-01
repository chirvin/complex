package com.test.thread;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 调用condition的方法前(比如await()、signal())，需要先获取锁，调用之后需要释放锁
 */
public class TestCondition {

    public static ReentrantLock lock = new ReentrantLock();
    public static Condition condition = lock.newCondition();

    public static void main(String[] args) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("my-pool-%d").build();
        ExecutorService threadPool = new ThreadPoolExecutor(5, 20,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory, new ThreadPoolExecutor.AbortPolicy());

        threadPool.execute(() -> {
            lock.lock();
            try {
                System.out.println("我是线程1");
                condition.await();
                System.out.println("1结束了");
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });

        threadPool.execute(() -> {
            lock.lock();
            try {
                System.out.println("我是线程2");
                condition.signal();
                System.out.println("2结束了");
            } catch (Exception e) {
            } finally {
                lock.unlock();
            }
        });

        threadPool.shutdown();
    }
}
