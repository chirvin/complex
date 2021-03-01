package com.test.jdk;

import com.test.thread.foo.MyThreadPool;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author scc
 * @date 2020/7/16 13:51
 */
public class ThreadCpuTest {

    public static void main(String[] args) {
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.submit(() -> {
            for (;;) {
                double a = new Random().nextDouble();
                double b = new Random().nextDouble();
                double c = a * b;
            }
        });

        threadPool.submit(() -> {
            for (;;) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
