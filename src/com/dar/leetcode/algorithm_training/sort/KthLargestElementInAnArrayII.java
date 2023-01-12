package com.dar.leetcode.algorithm_training.sort;

/**
 * @author :wx
 * @description : 215. 数组中的第K个最大元素 https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 * @create :2023-01-12 14:17:00
 */
public class KthLargestElementInAnArrayII {

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest = new KthLargestElementInAnArrayII().findKthLargest(nums, 4);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
       heapSort(nums,k);
       return nums[0];
    }

    //数组长度
    int heapLen;

    //数组交换
    private void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    //构建大根堆
    private void buildMaxHeap(int[] arr) {
        for (int i = heapLen / 2 - 1; i >= 0; i--) {
            maintainHeap(arr, i);
        }
    }

    //维护堆
    private void maintainHeap(int[] arr, int i) {
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
    public void heapSort(int[] arr,int k) {
        heapLen = arr.length;
        buildMaxHeap(arr);
        for (int i = arr.length - 1; i > 0; i--) {
            if(k==1){
                break;
            }
            swap(arr, 0, i);
            heapLen -= 1;
            maintainHeap(arr, 0);
            k--;
        }
    }
}
