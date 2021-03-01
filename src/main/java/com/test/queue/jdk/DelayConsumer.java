package com.test.queue.jdk;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.DelayQueue;

/**
 * @author scc
 * @date 2020/6/3 20:12
 */
public class DelayConsumer implements Runnable {

    private static final Logger LOGGER = LoggerFactory.getLogger(DelayConsumer.class);

    private final DelayQueue<DelayMessage> delayQueue;

    public DelayConsumer(DelayQueue<DelayMessage> delayQueue) {
        this.delayQueue = delayQueue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                // 从延迟队列的头部获取已经过期的消息, 如果暂时没有过期消息或者队列为空，则take()方法会被阻塞，直到有过期的消息为止
                DelayMessage delayMessage = delayQueue.take();
                LOGGER.info("Consumer received message: {}", delayMessage.getMessage());
            } catch (InterruptedException e) {
                LOGGER.error("线程中断", e);
            }
        }
    }
}
