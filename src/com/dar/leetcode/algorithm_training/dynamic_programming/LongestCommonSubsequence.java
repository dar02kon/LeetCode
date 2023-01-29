package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 1143. 最长公共子序列 https://leetcode.cn/problems/longest-common-subsequence/
 * @create :2023-01-29 09:22:00
 */
public class LongestCommonSubsequence {

    public static void main(String[] args) {

    }

    public int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length(), n = text2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1))//相等
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                else //不相等
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return dp[m][n];
    }
}
