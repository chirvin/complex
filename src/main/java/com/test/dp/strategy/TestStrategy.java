package com.test.dp.strategy;

/**
 * @author scc
 * @date 2020/7/8 20:36
 */
public class TestStrategy {

    public static void main(String[] args) {

        int a = 1;

        if (a == 1) {
            // 普通用户
            Strategy commonStrategy = new CommonStrategy();
            commonStrategy.discount();
        } else if (a == 2) {
            // Vip用户
            Strategy vipStrategy = new VipStrategy();
            vipStrategy.discount();
        }

//        StrategyContext context = new StrategyContext();
//        context.doStategy();
    }
}
