package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 64. 最小路径和 https://leetcode.cn/problems/minimum-path-sum/
 * @create :2023-01-26 20:34:00
 */
public class MinimumPathSum {

    public static void main(String[] args) {

    }

    public int minPathSum(int[][] grid) {
        int[] dp = new int[grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (j == 0) {//一直往下
                    dp[0] = dp[0] + grid[i][0];
                } else if (i == 0) {//一直往右
                    dp[j] = dp[j - 1] + grid[0][j];
                } else {//有两种走法，取路径短的
                    dp[j] = Math.min(dp[j - 1], dp[j]) + grid[i][j];
                }
            }
        }
        return dp[grid[0].length - 1];
    }
}
