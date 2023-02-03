package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 377. 组合总和 Ⅳ https://leetcode.cn/problems/combination-sum-iv/
 * @create :2023-02-03 17:36:00
 */
public class CombinationSumIV {

    public static void main(String[] args) {

    }

    public int combinationSum4(int[] nums, int target) {
        int[] dp = new int[target + 1];
        dp[0] = 1;
        for (int i = 1; i <= target; i++) {
            for (int num : nums) {
                if (i >= num) {
                    dp[i] += dp[i - num];
                }
            }
        }
        return dp[target];
    }
}
