package com.test.thread;

/**
 * @author scc
 * @date 2020/9/30 10:16
 */
public class RetentionTypeContext implements AutoCloseable {

    private static final ThreadLocal<String> ctx = new ThreadLocal<>();

    public RetentionTypeContext(String retentionType) {
        ctx.set(retentionType);
    }

    public static String currentRetentionType() {
        return ctx.get();
    }

    @Override
    public void close() {
        ctx.remove();
    }
}
