package com.test.al;

/**
 * 约瑟夫环
 * 反推法
 * @author scc
 * @date 2020/7/17 16:27
 */
public class Demo1 {

    public static int lastRemaining(int n, int m) {
        //存活的最后一个人的位置
        int last = 0;
        for (int i = 2; i <= n; i++) {
            last = (last + m) % i;
        }
        return last;
    }

    public static void main(String[] args) {
//        System.out.println(lastRemaining(5,3));
//        System.out.println(lastRemaining(10,17));
        System.out.println(7 % 2);
        System.out.println(7 / 2);
    }
}
