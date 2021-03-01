package com.test.sort;

import java.util.Arrays;

/**
 * @author scc
 * @date 2020/7/2 15:42
 */
public class BubbleSort {

    // HTTP 1.1 长连接、分块传输
    // HTTP 2 多路复用、头部压缩、服务端推送

    private static void sort1(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
                num++;
            }
        }
        System.out.println("总次数 = " + num);
    }

    private static void sort2(int[] array) {
        int num = 0;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    int tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                }
                num++;
            }
            if (isSorted) {
                break;
            }
        }
        System.out.println("总次数 = " + num);
    }

    private static void sort3(int[] array) {
        int num = 0;
        int tmp = 0;
        //记录最后一次交换的位置
        int lastExchangeIndex = 0;
        // 无序数列的边界，每次比较只需要比到这里为止
        int sortBorder = array.length - 1;
        for (int i = 0; i < array.length; i++) {
            //有序标记，每一轮的初始是true
            boolean isSorted = true;
            for (int j = 0; j < sortBorder; j++) {
                if (array[j] > array[j + 1]) {
                    tmp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = tmp;
                    //有元素交换，所以不是有序，标记变为false
                    isSorted = false;
                    // 把无序数列的边界更新为最后一次交换元素的位置
                    lastExchangeIndex = j;
                }
                num++;
            }
            sortBorder = lastExchangeIndex;
            if (isSorted) {
                break;
            }
        }
        System.out.println("总次数 = " + num);
    }

    public static void main(String[] args) {
        int[] array = new int[]{5, 8, 6, 3, 9, 1, 2, 7};
        sort3(array);
        System.out.println(Arrays.toString(array));
    }
}
