package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 62. 不同路径 https://leetcode.cn/problems/unique-paths/description/
 * @create :2023-01-26 10:29:00
 */
public class UniquePaths {

    public static void main(String[] args) {

    }

    public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        for (int i = 0; i <m ; i++) {
            for (int j = 1; j <n ; j++) {
                if(i==0){
                    dp[j]=dp[j-1];//一路向右
                } else {
                    dp[j] = dp[j]+dp[j-1];
                }
            }
        }
        return dp[n-1];
    }

}
