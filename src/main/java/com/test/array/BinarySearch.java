package com.test.array;

/**
 * @author scc
 * @date 2020/9/14 9:20
 */
public class BinarySearch {

    public static int missingNumber(int[] nums) {
        int i = 0, j = nums.length - 1;
        while(i <= j) {
            int m = (j - i) / 2 + i;
            if(nums[m] == m) {
                i = m + 1;
            } else {
                j = m - 1;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int[] arr = {0,1,2,3,5,6,7,8,9,10};
        System.out.println(missingNumber(arr));
    }

//    public static int binarySearch(int[] arr, int target) {
//        int start = 0;
//        int end = arr.length - 1;
//        while (start <= end) {
//            int mid = (end - start) / 2 + start;
//            if (arr[mid] == target) {
//                return mid;
//            } else if (arr[mid] < target) {
//                start = mid + 1;
//            } else {
//                end = mid - 1;
//            }
//        }
//        return -1;
//    }
//
//    public static int binary(int[] arr, int target, int start, int end) {
//        if (start <= end) {
//            int mid = (end - start) / 2 + start;
//            if (arr[mid] == target) {
//                return mid;
//            } else if (arr[mid] < target) {
//                return binary(arr,target,mid + 1,end);
//            } else {
//                return binary(arr,target,start,mid - 1);
//            }
//        }
//        return -1;
//    }
//
//    public static void main(String[] args) {
//        int[] arr = {1,2,3,5,6,7,8,9,10};
//        System.out.println(binarySearch(arr,10));
//        System.out.println(binary(arr,10,0,arr.length - 1));
//    }
}
