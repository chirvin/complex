package com.test.atomic;

import com.test.thread.foo.MyThreadPool;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * @author scc
 * @date 2020/6/18 11:32
 */
public class AtomicStampedReferenceTest {

    private static AtomicStampedReference<Integer> atomicStampedRef =
            new AtomicStampedReference<>(1, 0);

    public static void main(String[] args) {
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            System.out.println("操作线程A : " + Thread.currentThread() + ", 初始值 a = " + atomicStampedRef.getReference());
            //获取当前标识别
            int stamp = atomicStampedRef.getStamp();
            try {
                //等待3秒 ，以便让干扰线程执行
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            //此时expectedReference未发生改变，但是stamp已经被修改了,所以CAS失败
            boolean isCASSuccess = atomicStampedRef
                    .compareAndSet(1, 2, stamp, stamp + 1);
            System.out.println("操作线程A : " + Thread.currentThread() + ", CAS操作结果: " + isCASSuccess);
        });

        threadPool.execute(() -> {
            atomicStampedRef
                    .compareAndSet(1, 2,
                            atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("干扰线程B : " + Thread.currentThread() + ",【increment】, 值 = " + atomicStampedRef.getReference());
            atomicStampedRef
                    .compareAndSet(2, 1,
                            atomicStampedRef.getStamp(), atomicStampedRef.getStamp() + 1);
            System.out.println("干扰线程B : " + Thread.currentThread() + ",【decrement】, 值 = " + atomicStampedRef.getReference());
        });
    }
}
