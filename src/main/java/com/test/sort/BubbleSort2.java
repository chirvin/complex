package com.test.sort;

import java.util.Arrays;

/**
 * @author scc
 * @date 2020/7/13 16:52
 */
public class BubbleSort2 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 3, 9, 1, 2, 7};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void bubbleSort(int[] arr) {
        boolean isSorted = true;
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    isSorted = false;
                }
            }
            if (isSorted) {
                break;
            }
        }
    }
}
