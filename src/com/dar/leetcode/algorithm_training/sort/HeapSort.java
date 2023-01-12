package com.dar.leetcode.algorithm_training.sort;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 堆排序
 * @create :2023-01-12 11:33:00
 */
public class HeapSort {
    //数组长度
    static int heapLen;

    //数组交换
    private static void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //构建大根堆
    private static void buildMaxHeap(int[] arr) {
        for (int i = heapLen / 2 - 1; i >= 0; i--) {
            maintainHeap(arr, i);
        }
    }

    //维护堆
    private static void maintainHeap(int[] arr, int i) {
        int left = i * 2 + 1;
        int right = i * 2 + 2;
        int position = i;
        if (left < heapLen && arr[position] < arr[left]) {
            position = left;
        }
        if (right < heapLen && arr[position] < arr[right]) {
            position = right;
        }
        if (position != i) {
            swap(arr, position, i);
            maintainHeap(arr, position);//判断是否调整完成
        }
    }

    //堆排序
    public static void heapSort(int[] arr) {
        heapLen = arr.length;
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            swap(arr, 0, i);
            heapLen -= 1;
            maintainHeap(arr, 0);
        }
    }

    public static void main(String[] args) {
        int[] nums = {1,3,4,2,5,6,9,8,0};
        heapSort(nums);
        System.out.println(Arrays.toString(nums));
    }
}
