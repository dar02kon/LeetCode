package com.dar.codetop;

/**
 * @author :wx
 * @description : 88. 合并两个有序数组 https://leetcode.cn/problems/merge-sorted-array/description/
 * @create :2023-07-17 22:26:00
 */
public class MergeSortedArray {
    /**
     * 从后往前填充，选最大值
     */
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int left = m - 1;
        int right = n - 1;
        int index = m + n - 1;
        while (left >= 0 || right >= 0) {
            if (left < 0) nums1[index--] = nums2[right--];
            else if (right < 0) nums1[index--] = nums1[left--];
            else if (nums1[left] > nums2[right]) nums1[index--] = nums1[left--];
            else nums1[index--] = nums2[right--];
        }
    }
}
