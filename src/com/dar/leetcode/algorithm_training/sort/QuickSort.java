package com.dar.leetcode.algorithm_training.sort;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 快速排序
 * @create :2023-01-12 10:01:00
 */
public class QuickSort {

    /**
     * 将选取的中心元素移到中间（左边均小于等于，右边均大于等于）
     * @param array
     * @param low
     * @param high
     * @return
     */
    public static int partition(int[] array, int low, int high){
        int center = array[high];
        int position = low;
        for (int i = low; i < high; i++) {
            if(array[i]<=center){//将较小的元素与较大的元素进行交换
                // 将比中心元素小的元素和指针指向的元素交换位置
                // 如果第一个元素比中心元素小，这里就是自己和自己交换位置，指针和索引都向下一位移动
                // 如果元素比中心元素大，索引向下移动，直到找到比中心元素小的元素，交换位置，指针向下移动
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

    public static void quickSort(int[] array, int low, int high) {
        if(low<high){
            //获取划分边界
            int position = partition(array,low,high);
            //递归排序左子数组
            quickSort(array,low,position-1);
            //递归排序右子数组
            quickSort(array,position+1,high);
        }
    }

    public static void main(String[] args) {
        int[] nums = {2,3,4,7,5,1,8,9,0};
        quickSort(nums,0,nums.length-1);
        System.out.println(Arrays.toString(nums));
    }
}
