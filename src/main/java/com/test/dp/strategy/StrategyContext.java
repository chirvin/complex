package com.test.dp.strategy;

/**
 * @author scc
 * @date 2020/7/8 20:35
 */
public class StrategyContext {

    // 默认策略
    private Strategy strategy = new CommonStrategy();

    public StrategyContext(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doStategy() {
        strategy.discount();
    }
}
