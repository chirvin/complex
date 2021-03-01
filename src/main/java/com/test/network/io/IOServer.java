package com.test.network.io;

import com.test.thread.foo.MyThreadPool;

import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;

/**
 * @author scc
 * @date 2020/6/12 13:54
 */
public class IOServer {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8000);
        ExecutorService threadPool = MyThreadPool.getThreadPool();
        threadPool.execute(() -> {
            // 死循环获取连接
            while (true) {
                try {
                    Socket socket = serverSocket.accept();
                    threadPool.execute(() -> {
                        // 读写数据
                        try {
                            byte[] data = new byte[1024];
                            InputStream inputStream = socket.getInputStream();
                            while (true) {
                                int len;
                                while ((len = inputStream.read(data)) != -1) {
                                    System.out.println(new String(data, 0, len));
                                }
                            }
                        } catch (Exception e) {

                        }
                    });
                } catch (Exception e) {

                }
            }
        });
    }
}
