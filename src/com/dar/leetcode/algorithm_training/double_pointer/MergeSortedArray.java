package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 88. 合并两个有序数组 https://leetcode.cn/problems/merge-sorted-array/description/
 * @create :2023-01-11 09:24:00
 */
public class MergeSortedArray {

    public static void main(String[] args) {

    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1, j = n - 1, index = m + n - 1;
        while (i >= 0 || j >= 0) {
            if (i < 0) nums1[index--] = nums2[j--];
            else if (j < 0) nums1[index--] = nums1[i--];
            else if (nums1[i] < nums2[j]) nums1[index--] = nums2[j--];
            else nums1[index--] = nums1[i--];
        }
    }
}
