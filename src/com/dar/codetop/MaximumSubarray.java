package com.dar.codetop;

/**
 * @author :wx
 * @description : 53. 最大子数组和 https://leetcode.cn/problems/maximum-subarray/description/
 * @create :2023-07-13 21:54:00
 */
public class MaximumSubarray {
    public int maxSubArray(int[] nums) {
        // dp用于记录前面的最大子数组和
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            // 如果前面的最大子数组和是负数，则直接放弃前面，选择重新开始（加负数肯定越加越小）
            dp = dp < 0 ? nums[i] : dp + nums[i];
            // 每次循环都需要记录最大值，防止加了一个负数导致最大值丢失
            max = Math.max(dp, max);
        }
        return max;
    }
}
