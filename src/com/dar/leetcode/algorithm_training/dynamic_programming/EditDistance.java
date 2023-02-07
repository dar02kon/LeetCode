package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 72. 编辑距离 https://leetcode.cn/problems/edit-distance/description/
 * @create :2023-02-07 10:58:00
 */
public class EditDistance {

    public static void main(String[] args) {

    }

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            dp[i][0] = dp[i - 1][0] + 1;//删除操作
        }

        for (int i = 1; i <= n; i++) {
            dp[0][i] = dp[0][i - 1] + 1;//插入操作
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];//不做操作
                } else {
                    dp[i][j] = Math.min(Math.min(dp[i - 1][j - 1], dp[i - 1][j]), dp[i][j - 1]) + 1;//选择需要操作次数最少的
                }
            }
        }
        return dp[m][n];
    }

}
