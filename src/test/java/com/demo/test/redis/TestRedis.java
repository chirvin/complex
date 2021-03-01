package com.demo.test.redis;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import java.util.*;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

/**
 * @author scc
 * @date 2020/7/10 15:13
 */
public class TestRedis {

    private Jedis jedis;

    @Before
    public void init() {
        jedis = new Jedis("127.0.0.1",6379);
    }

    @After
    public void finish() { jedis.close(); }

    @Test
    public void testString() {
        jedis.set("name", "xiaoxiao");
        jedis.del("name");
        String name = jedis.get("name");
        assertThat(name, equalTo(null));

        jedis.setex("age", 5, "10");
        jedis.incr("age");
        String age = jedis.get("age");
        assertThat(age, equalTo("11"));
    }

    @Test
    public void testDistributeLock() {
        String lockKey = "111";
        String requestId = UUID.randomUUID().toString();
        // 加锁
        String lockResult = jedis.set(lockKey, requestId, "NX", "PX", 10);
        assertThat(lockResult, equalTo("OK"));
        // 释放锁
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object unLockResult = jedis.eval(script, Collections.singletonList(lockKey), Collections.singletonList(requestId));
        assertThat(unLockResult, equalTo(1L));
    }

    @Test
    public void testHash() {
        jedis.hset("1","name","xiaoming");
        jedis.hset("1","age","10");
        jedis.hdel("1", "name");
        Map<String,String> result = jedis.hgetAll("1");
        System.out.println(result);
    }

    @Test
    public void testList() {
        // 队列，左进右出，先进先出，当然也可以用作栈
        jedis.lpush("ids", "1");
//        jedis.lpush("ids", "2", "3");
        String result = jedis.rpop("ids");
        System.out.println(result);
    }

    @Test
    public void testSet() {
        // 维护中奖ID
        jedis.sadd("cIds","11");
        Set<String> result = jedis.smembers("cIds");
        System.out.println(result);
        System.out.println(jedis.sismember("cIds","11"));
        jedis.srem("cIds","11");
        System.out.println(jedis.sismember("cIds","11"));
    }

    @Test
    public void testZset() {
        // 粉丝列表、成绩排名等
        jedis.zadd("fens", 2017d, "xiaoxiao");
        jedis.zadd("fens", 2018d, "xiaobai");
        jedis.zrem("fens","xiaoming");
        System.out.println(jedis.zscore("fens","xiaobai"));
        System.out.println(jedis.zrange("fens",0,-1));
        System.out.println(jedis.zrangeByScore("fens",2018d,2019d));
    }

    @Test
    public void testHyperLogLog() {
        for (int i = 0; i < 10000; i++) {
            jedis.pfadd("codehole", "user" + i);
        }
        System.out.println(jedis.pfcount("codehole"));
    }
}
