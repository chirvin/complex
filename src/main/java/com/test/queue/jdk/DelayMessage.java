package com.test.queue.jdk;

import lombok.Data;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 延迟消息实体类
 * @author scc
 * @date 2020/6/3 10:49
 */
@Data
public class DelayMessage implements Delayed {

    // 延迟任务
    private String message;

    // 任务过期时间
    private Long ttl;

    public DelayMessage(String message, Long ttl) {
        this.message = message;
        this.ttl = System.currentTimeMillis() + ttl;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        // 计算任务距离过期还剩多少时间
        long remaining = ttl - System.currentTimeMillis();
        return unit.convert(remaining, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        // 比较排序，将延时时间最小的任务放到队伍头部
        return (int) (this.getDelay(TimeUnit.MILLISECONDS) - o.getDelay(TimeUnit.MILLISECONDS));
    }
}
