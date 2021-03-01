package com.test.atomic;

import com.test.thread.foo.MyThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author scc
 * @date 2020/6/18 11:32
 */
public class AtomicIntegerTest {

    public static AtomicInteger atomicInteger = new AtomicInteger(1);

    public static void main(String[] args) {
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            System.out.println("操作线程A : " + Thread.currentThread() + ", 初始值 = " + atomicInteger);  //定义变量 a = 1
            try {
                Thread.sleep(3000);  //等待3秒 ，以便让干扰线程执行
            } catch (InterruptedException e) {
            }
            boolean isCASSuccess = atomicInteger.compareAndSet(1, 2); // CAS操作
            System.out.println("操作线程A : " + Thread.currentThread() + ", CAS操作结果: " + isCASSuccess);
        });

        threadPool.execute(() -> {
            atomicInteger.incrementAndGet(); // a 加 1, a + 1 = 1 + 1 = 2
            System.out.println("干扰线程B : " + Thread.currentThread() + ",【increment】, 值 = " + atomicInteger);
            atomicInteger.decrementAndGet(); // a 减 1, a - 1 = 2 - 1 = 1
            System.out.println("干扰线程B : " + Thread.currentThread() + ",【decrement】, 值 = " + atomicInteger);
        });
    }
}
