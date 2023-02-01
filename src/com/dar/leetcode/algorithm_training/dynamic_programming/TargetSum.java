package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 494. 目标和 https://leetcode.cn/problems/target-sum/description/
 * @create :2023-02-01 20:21:00
 */
public class TargetSum {

    public static void main(String[] args) {
        int targetSumWays = new TargetSum().findTargetSumWays(new int[]{1, 1, 1, 1, 1}, 3);
        System.out.println(targetSumWays);
    }

    public int findTargetSumWays(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum < target || -sum > target || (sum + target) % 2 != 0) {
            return 0;
        }
        target = (target + sum) / 2;
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int num : nums) {
            for (int j = target; j >= num; j--) {
                dp[j] += dp[j - num];
            }
        }
        return dp[target];
    }

    public int findTargetSumWays2(int[] nums, int target) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        // 特殊情况
        if (sum < target || -sum > target || (sum + target) % 2 != 0) {
            return 0;
        }
        target = (target + sum) / 2;
        int[][] dp = new int[nums.length+1][target+1];
        dp[0][0] = 1;//初始条件
        for (int i = 1; i <= nums.length; i++) {// 下标从1开始为了方便表示
            int num = nums[i - 1];
            for (int j = 0; j <= target; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j >= num) {
                    dp[i][j] += dp[i - 1][j - num];
                }
            }
        }
        return dp[nums.length][target];
    }
}
