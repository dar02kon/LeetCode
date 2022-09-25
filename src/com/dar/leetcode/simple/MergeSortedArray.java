package com.dar.leetcode.simple;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 合并两个有序数组 https://leetcode.cn/problems/merge-sorted-array/
 * @create :2022-09-25 20:09:00
 */
public class MergeSortedArray {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5,6,0,0,0};
        int[] nums2 = {1,2,3};
        new MergeSortedArray().merge(nums1,6,nums2,3);
        System.out.println(Arrays.toString(nums1));
    }

    /**
     * 双指针i，j分别指向两个数组的末端，
     * 进行比较将大的值放入数组nums1末端（可以再开一个变量记录这个位置，或者用i+j+1代替也行）
     * 谁大就移动谁
     * while循环结束后，如果nums2还没有遍历完，
     * 直接将剩余的数放到nums1对应的位置
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int num = m+n-1;//i+j+1
        while (i>=0&&j>=0){
            if(nums1[i]>=nums2[j]){
                nums1[num] = nums1[i];
                i--;
            } else {
                nums1[num] = nums2[j];
                j--;
            }
            num--;
        }
        if(i<0&&j> -1){
            System.arraycopy(nums2, 0, nums1, 0, j + 1);
        }
    }
}
