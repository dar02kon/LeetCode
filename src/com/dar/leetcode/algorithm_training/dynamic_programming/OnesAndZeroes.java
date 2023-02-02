package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 474. 一和零 https://leetcode.cn/problems/ones-and-zeroes/
 * @create :2023-02-02 10:33:00
 */
public class OnesAndZeroes {

    public static void main(String[] args) {

    }

    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int[strs.length+1][m+1][n+1];
        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i - 1];
            int zero = 0, one = 0;
            // 统计 0 和 1 的个数
            for (int k = 0; k < str.length() ; k++) {
                if(str.charAt(k)=='0'){
                    zero++;
                } else {
                    one++;
                }
            }
            for (int j = 0; j <= m ; j++) {
                for (int k = 0; k <= n ; k++) {
                    if(zero<=j&&one<=k){
                       dp[i][j][k] = Math.max(dp[i-1][j][k],dp[i-1][j-zero][k-one]+1);
                    } else {
                        dp[i][j][k] = dp[i-1][j][k];
                    }
                }
            }
        }
        return dp[strs.length][m][n];
    }

    public int findMaxForm2(String[] strs, int m, int n) {
        int[][] dp = new int[m+1][n+1];
        for (int i = 1; i <= strs.length; i++) {
            String str = strs[i - 1];
            int zero = 0, one = 0;
            for (int k = 0; k < str.length() ; k++) {
                if(str.charAt(k)=='0'){
                    zero++;
                } else {
                    one++;
                }
            }
            for (int j = m; j >= zero ; j--) {
                for (int k = n; k >= one ; k--) {

                    dp[j][k] = Math.max(dp[j][k],dp[j-zero][k-one]+1);
                }
            }
        }
        return dp[m][n];
    }


}
