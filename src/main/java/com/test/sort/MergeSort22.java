package com.test.sort;

import java.util.Arrays;

/**
 * @author scc
 * @date 2020/7/13 17:52
 */
public class MergeSort22 {

    public static void main(String[] args) {
        int[] arr = new int[]{5, 8, 6, 3, 9, 1, 2, 7};
        mergeSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    public static void mergeSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            int midIndex = (endIndex - startIndex) / 2 + startIndex;
            mergeSort(arr, startIndex, midIndex);
            mergeSort(arr, midIndex + 1, endIndex);
            merge(arr, startIndex, midIndex, endIndex);
        }
    }

    public static void merge(int[] arr, int startIndex, int midIndex, int endIndex) {
        int[] tempArr = new int[endIndex - startIndex + 1];
        int p1 = startIndex, p2 = midIndex + 1, p = 0;
        while (p1 <= midIndex && p2 <= endIndex) {
            if (arr[p1] <= arr[p2]) {
                tempArr[p++] = arr[p1++];
            } else {
                tempArr[p++] = arr[p2++];
            }
        }
        while (p1 <= midIndex) {
            tempArr[p++] = arr[p1++];
        }
        while (p2 <= endIndex) {
            tempArr[p++] = arr[p2++];
        }
        for (int i = 0; i < tempArr.length; i++) {
            arr[startIndex + i] = tempArr[i];
        }
    }
}
