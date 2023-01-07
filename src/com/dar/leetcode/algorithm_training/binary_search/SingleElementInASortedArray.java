package com.dar.leetcode.algorithm_training.binary_search;

/**
 * @author :wx
 * @description : 540. 有序数组中的单一元素 https://leetcode.cn/problems/single-element-in-a-sorted-array/description/
 * @create :2023-01-07 10:39:00
 */
public class SingleElementInASortedArray {

    public static void main(String[] args) {

    }

    public int singleNonDuplicate(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (mid % 2 == 1) mid--;//调整到偶数位
            if (nums[mid] == nums[mid + 1]) left = mid + 2;//说明mid在目标元素左边
            else right = mid;//mid在目标元素右边，同时mid可能就是目标元素，所以right=mid
        }
        return nums[left];//最终left和right都指向目标元素
    }
}
