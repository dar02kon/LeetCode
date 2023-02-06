package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 53. 最大子数组和 https://leetcode.cn/problems/maximum-subarray/
 * @create :2023-02-06 10:29:00
 */
public class MaximumSubarray {

    public static void main(String[] args) {

    }

    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i-1]<0?nums[i]:dp[i-1]+nums[i];
            max = Math.max(max,dp[i]);
        }
        return max;
    }

    // 优化空间
    public int maxSubArray2(int[] nums) {
        int dp = nums[0];
        int max = dp;
        for (int i = 1; i < nums.length; i++) {
            dp = dp<0?nums[i]:dp+nums[i];
            max = Math.max(max,dp);
        }
        return max;
    }

}
