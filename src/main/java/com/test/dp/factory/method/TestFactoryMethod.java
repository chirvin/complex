package com.test.dp.factory.method;

/**
 * @author scc
 * @date 2020/7/8 20:25
 */
public class TestFactoryMethod {

    public static void main(String[] args) {

        Factory factory = new DellFactory();
        Mouse mouse = factory.createMouse();
        Mouse mouse1 = factory.createMouse();
        mouse.sayHi();
        mouse1.sayHi();

        Factory factory2 = new LenoveFactory();
        Mouse mouse2 = factory2.createMouse();
        mouse2.sayHi();
    }
}
