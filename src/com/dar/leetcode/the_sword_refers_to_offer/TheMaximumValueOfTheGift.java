package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 47. 礼物的最大价值 https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/?favorite=xb9nqhhg
 * @create :2022-12-18 16:12:00
 */
public class TheMaximumValueOfTheGift {

    public static void main(String[] args) {

    }

    public int maxValue(int[][] grid) {
        int[][] dp = new int[grid.length][grid[0].length];
        dp[0][0] = grid[0][0];
        for (int i = 1; i <grid[0].length ; i++) {
            dp[0][i] = dp[0][i-1]+grid[0][i];
        }
        for (int i = 1; i <grid.length ; i++) {
            dp[i][0] = dp[i-1][0]+grid[i][0];
        }
        for (int i = 1; i < grid.length; i++) {
            for (int j = 1; j < grid[0].length; j++) {
                dp[i][j] = Math.max(dp[i-1][j],dp[i][j-1])+grid[i][j];
            }
        }
        return dp[grid.length-1][dp[0].length-1];
    }
}
