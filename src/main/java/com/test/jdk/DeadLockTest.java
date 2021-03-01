package com.test.jdk;

/**
 * @author scc
 * @date 2020/7/16 17:00
 */
public class DeadLockTest {

    public static void main(String[] args) {
        lockTest();
    }

    private static void lockTest() {
        Object o1 = new Object();
        Object o2 = new Object();
        new Thread(() -> {
            synchronized (o1) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o2) {
                    System.out.println("deadLock-1");
                }
            }
        },"deadLock-1").start();
        new Thread(() -> {
            synchronized (o2) {
                try {
                    Thread.sleep(500);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                synchronized (o1) {
                    System.out.println("deadLock-2");
                }
            }
        },"deadLock-2").start();
    }
}
