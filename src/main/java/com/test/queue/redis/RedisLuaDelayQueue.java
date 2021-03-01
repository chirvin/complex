package com.test.queue.redis;

import com.test.thread.foo.MyThreadPool;
import redis.clients.jedis.Jedis;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.UUID;
import java.util.concurrent.ExecutorService;

/**
 * redis + lua实现
 * @author scc
 * @date 2020/7/14 20:23
 */
public class RedisLuaDelayQueue {

    public static void main(String[] args) {

        final Jedis jedis = new Jedis("127.0.0.1", 6379);
        try {
            // 生产
            String key = "delay_queue";
            // 订单ID
            String orderId1 = UUID.randomUUID().toString();
            String orderId2 = UUID.randomUUID().toString();
            String orderId3 = UUID.randomUUID().toString();
            System.out.println("时间：" + new Date() + ", 生产延迟消息");
            jedis.zadd(key, System.currentTimeMillis() + 3 * 1000, orderId1);
            jedis.zadd(key, System.currentTimeMillis() + 6 * 1000, orderId2);
            jedis.zadd(key, System.currentTimeMillis() + 6 * 1000, orderId3);

            // 消费
            String script = "local result = redis.call('zrangebyscore', KEYS[1], 0, ARGV[1], 'limit', 0, 1)\n" +
                    "if #result > 0 then\n" +
                    "    if redis.call('zrem', KEYS[1], result[1]) > 0 then\n" +
                    "        return result[1]\n" +
                    "    else\n" +
                    "        return ''\n" +
                    "    end\n" +
                    "else\n" +
                    "    return ''\n" +
                    "end";

            ExecutorService threadPool = MyThreadPool.getThreadPool();
            threadPool.execute(() -> {
                while (true) {
                    Object result = jedis.eval(script, Collections.singletonList(key),
                            Collections.singletonList(String.valueOf(System.currentTimeMillis())));
                    if (result == null || Objects.equals("", result)) {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            break;
                        }
                    } else {
                        System.out.println("时间：" + new Date() + ", 消费订单：" + result);
                        // TODO 开始业务处理
                    }
                }
            });

        } catch (Exception e) {

        } finally {
            jedis.close();
        }
    }
}
