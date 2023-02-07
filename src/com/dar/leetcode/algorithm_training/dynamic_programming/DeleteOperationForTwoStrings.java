package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 583. 两个字符串的删除操作 https://leetcode.cn/problems/delete-operation-for-two-strings/
 * @create :2023-02-07 10:30:00
 */
public class DeleteOperationForTwoStrings {

    public static void main(String[] args) {

    }

    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }

            }
        }
        return m + n - 2 * dp[m][n];
    }

}
