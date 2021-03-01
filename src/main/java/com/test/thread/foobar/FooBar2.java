package com.test.thread.foobar;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 交替打印，使用公平锁，需要公平锁吗
 */
public class FooBar2 {

    private int n;

    public FooBar2(int n) {
        this.n = n;
    }

    Lock lock = new ReentrantLock();
    volatile boolean permitFoo = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if(permitFoo) {
                    printFoo.run();
                    i++;
                    permitFoo = false;
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; ) {
            lock.lock();
            try {
                if(!permitFoo) {
                    printBar.run();
                    i++;
                    permitFoo = true;
                }
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        FooBar2 fooBar = new FooBar2(300);
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.foo(new Runnable() {
                        @Override
                        public void run() {
                            System.out.print("foo");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    fooBar.bar(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("bar");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();
        thread1.start();
    }
}
