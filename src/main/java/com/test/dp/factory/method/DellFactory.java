package com.test.dp.factory.method;

/**
 * @author scc
 * @date 2020/7/8 20:24
 */
public class DellFactory implements Factory {
    @Override
    public Mouse createMouse() {
        return new DellMouse();
    }
}
