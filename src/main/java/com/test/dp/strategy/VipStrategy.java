package com.test.dp.strategy;

/**
 * @author scc
 * @date 2020/7/8 20:34
 */
public class VipStrategy implements Strategy {
    @Override
    public void discount() {
        System.out.println("会员打五折");
    }
}
