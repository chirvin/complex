package com.test.thread.foobar;

import com.test.thread.foo.MyThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Semaphore;

/**
 * 交替打印，使用Semaphore
 * Semaphore为0时，acquire时会阻塞，
 * release时会增加1
 */
public class FooBar1 {

    private int n;

    public FooBar1(int n) {
        this.n = n;
    }

    Semaphore foo = new Semaphore(1);
    Semaphore bar = new Semaphore(0);

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            foo.acquire();
            printFoo.run();
            bar.release();
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            bar.acquire();
            printBar.run();
            foo.release();
        }
    }

    public static void main(String[] args) {
        FooBar1 fooBar1 = new FooBar1(1);
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            try {
                fooBar1.foo(() -> {
                    System.out.print("foo");
                });
            } catch (InterruptedException e) {
            }
        });
        threadPool.execute(() -> {
            try {
                fooBar1.bar(() -> {
                    System.out.print("bar");
                });
            } catch (InterruptedException e) {
            }
        });
    }
}
