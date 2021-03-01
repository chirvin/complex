package com.test.sort;

import java.util.Arrays;

/**
 * @author scc
 * @date 2020/7/2 15:08
 * 快速排序：指针交换法
 */
public class QuickSort222 {

    public static void main(String[] args) {
        int[] arr = new int[]{4, 7, 6, 5, 3, 2, 8, 1};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }
        // 获取基准元素索引
        int pivotIndex = partition(arr, startIndex, endIndex);
        // 分治法
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    public static int partition(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            while (left < right && arr[right] > pivot) {
                right--;
            }
            while (left < right && arr[left] <= pivot) {
                left++;
            }
            if (left < right) {
                int temp = arr[left];
                arr[left] = arr[right];
                arr[right] = temp;
            }
        }
        // 交换pivot和重合点
        int temp = pivot;
        arr[startIndex] = arr[left];
        arr[left] = temp;
        return left;
    }
}
