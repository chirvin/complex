package com.test.network.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author scc
 * @date 2020/6/12 15:00
 */
public class NettyClient {

    private static boolean allowCoreThreadTimeOut;

    public static void main(String[] args) throws InterruptedException {

        HashMap map = new HashMap();

        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap();

        LinkedHashMap linkedHashMap = new LinkedHashMap();

        List list = new ArrayList<>();

        CopyOnWriteArrayList copyOnWriteArrayList = new CopyOnWriteArrayList();

        Bootstrap bootstrap = new Bootstrap();
        NioEventLoopGroup group = new NioEventLoopGroup();
        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel channel) throws Exception {
                        channel.pipeline().addLast(new StringEncoder());
                    }
                });
        Channel channel = bootstrap.connect("127.0.0.1", 8000).channel();
        while (true) {
            channel.writeAndFlush(new Date() + " : Hello World!");
                Thread.sleep(2000);
        }
    }
}
