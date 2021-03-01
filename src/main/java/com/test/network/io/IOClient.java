package com.test.network.io;

import com.test.thread.foo.MyThreadPool;

import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;

/**
 * @author scc
 * @date 2020/6/12 14:08
 */
public class IOClient {
//    public static void main(String[] args) {
//        new Thread(() -> {
//            try {
//                Socket socket = new Socket("127.0.0.1", 8000);
//                while (true) {
//                    try {
//                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
//                        socket.getOutputStream().flush();
//                        Thread.sleep(2000);
//                    } catch (Exception e) {
//                    }
//                }
//            } catch (IOException e) {
//            }
//        }).start();
//    }

    public static void main(String[] args) {
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + " : Hello word").getBytes());
                        socket.getOutputStream().flush();
                        Thread.sleep(2000);
                    } catch (Exception e) {

                    }
                }
            } catch (Exception e) {

            }
        });
    }
}
