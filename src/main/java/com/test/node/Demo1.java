package com.test.node;

/**
 * @author scc
 * @date 2020/9/7 10:30
 * 斐波那契数列和约瑟夫环
 */
public class Demo1 {

    public static int fib(int n) {
        if (n == 0 || n == 1) {
            return n;
        }
        int a = 1, b = 0;
        for (int i = 1; i < n; i++) {
            a = a + b;
            b = a - b;
            a %= 1000000007;
        }
        return a;
    }

    public static void main(String[] args) {
        System.out.println("斐波那契数列之和=" + fib(5));
        System.out.println("最后一个活着的人的索引=" + lastRemaining(5,3));
    }

//    n=5,m=3
//
//            0 1 2 3 4 0 1 2 3 4			index为1 数组个数为5  （0 + 3）% 5 = 3；
//            3 4 0 1 3 4 0 1				index为3 数组个数为4  （1 + 3）% 4 = 0
//            1 3 4 1 3 4					index为0 数组个数为3  （1+ 3）% 3 = 1
//            1 3 1 3						index为0 数组个数为2 （0+3） % 2 = 1
//            3							index为0 数组个数为1

    public static int lastRemaining(int n, int m) {
        int result = 0;
        for(int i = 2; i <= n; i++) {
            result = (result + m) % i;
        }
        return result;
    }
}
