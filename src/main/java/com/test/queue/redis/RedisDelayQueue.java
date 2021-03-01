package com.test.queue.redis;

import com.test.thread.foo.MyThreadPool;
import redis.clients.jedis.Jedis;
import java.util.*;
import java.util.concurrent.ExecutorService;

/**
 * @author scc
 * @date 2020/7/14 15:42
 */
public class RedisDelayQueue {

    public static void main(String[] args) {

        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {
            String key = "delay_queue";
            // 订单ID
            String orderId1 = UUID.randomUUID().toString();
            String orderId2 = UUID.randomUUID().toString();
            System.out.println("时间：" + new Date() + ", 生产延迟消息");
            jedis.zadd(key, System.currentTimeMillis() + 3 * 1000, orderId1);
            jedis.zadd(key, System.currentTimeMillis() + 6 * 1000, orderId2);

            ExecutorService threadPool = MyThreadPool.getThreadPool();
            threadPool.execute(() -> {
                while (true) {
                    Set<String> set;
                    // 只获取第一条数据, 只获取不会移除数据
                    set = jedis.zrangeByScore(key, 0, System.currentTimeMillis(), 0, 1);
                    if (set.size() == 0) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } else {
                        // 移除数据获取到的数据
                        List<String> list = new ArrayList<>();
                        for (String id : set) {
                            list.add(id);
                        }
                        if (jedis.zrem(key, list.get(0)) > 0) {
                            System.out.println("时间：" + new Date() + ", 消费订单：" + list.get(0));
                        }
                    }
                }
            });

        } catch (Exception e) {
        } finally {
            jedis.close();
        }
    }
}
