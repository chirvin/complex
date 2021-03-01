package com.test.dp.strategy;

/**
 * @author scc
 * @date 2020/7/8 20:33
 */
public class CommonStrategy implements Strategy {
    @Override
    public void discount() {
        System.out.println("普通用户满一百减二十");
    }
}
