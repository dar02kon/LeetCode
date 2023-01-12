package com.dar.leetcode.algorithm_training.sort;

/**
 * @author :wx
 * @description : 215. 数组中的第K个最大元素 https://leetcode.cn/problems/kth-largest-element-in-an-array/description/
 * @create :2023-01-12 14:17:00
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        int kthLargest = new KthLargestElementInAnArray().findKthLargest(nums, 4);
        System.out.println(kthLargest);
    }

    public int findKthLargest(int[] nums, int k) {
        quickSort(nums,0, nums.length-1,k-1);
        return nums[k-1];
    }

    public int partition(int[] array, int low, int high){
        int center = array[high];
        int position = low;
        for (int i = low; i < high; i++) {
            if(array[i]>=center){
                int temp = array[i];
                array[i] = array[position];
                array[position] = temp;
                position++;
            }
        }
        //将选取的中心元素移到”中间“
        int temp = array[high];
        array[high] = array[position];
        array[position] = temp;
        return position;
    }

    public  void quickSort(int[] array, int low, int high,int k) {
        if(low<high){
            int position = partition(array,low,high);
            //根据position与k的大小关系选择左子数组还是右子数组进行排序
            if(position>k){
                quickSort(array,low,position-1,k);
            } else {
                quickSort(array,position+1,high,k);
            }
        }
    }

}
