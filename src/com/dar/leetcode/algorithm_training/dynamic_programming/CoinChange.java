package com.dar.leetcode.algorithm_training.dynamic_programming;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 322. 零钱兑换 https://leetcode.cn/problems/coin-change/
 * @create :2023-02-02 11:39:00
 */
public class CoinChange {

    public static void main(String[] args) {

    }

    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);// 要求最小值，初始值就得大
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int coin : coins) {// 倒序或者正序结果都一样，硬币可以重复且数量不限
                if (coin <= i) {
                    dp[i] = Math.min(dp[i], dp[i - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
