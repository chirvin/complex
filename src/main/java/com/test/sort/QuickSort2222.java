package com.test.sort;

import java.util.Arrays;

/**
 * @author scc
 * @date 2020/7/13 17:01
 */
public class QuickSort2222 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 3, 9, 1, 2, 7};
        quickSort(arr, 0, arr.length -1);
        System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            // 获取基准元素索引
            int pivotIndex = partition(arr,startIndex,endIndex);
            // 分治法
            quickSort(arr,startIndex,pivotIndex - 1);
            quickSort(arr,pivotIndex + 1,endIndex);
        }
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
        // pivot和重合点left交换
        int temp = arr[startIndex];
        arr[startIndex] = arr[left];
        arr[left] = temp;
        return left;
    }
}
