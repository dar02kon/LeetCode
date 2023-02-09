package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 650. 只有两个键的键盘 https://leetcode.cn/problems/2-keys-keyboard/description/
 * @create :2023-02-09 13:12:00
 */
public class TwoKeysKeyboard {

    public static void main(String[] args) {

    }

    public int minSteps(int n) {
        int[] dp = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            dp[i] = i;
            for (int j = i / 2 + 1; j >= 1; j--) {
                if (i % j == 0) {
                    dp[i] = dp[j] + dp[i / j];
                    break;
                }
            }
        }
        return dp[n];
    }

    public int minSteps2(int n) {
        int result = 0;
        for (int i = 2; i * i <= n; i++) {
            while (n % i == 0) {// 除尽
                result += i;
                n /= i;
            }
        }
        if (n != 1) {
            result += n;
        }
        return result;
    }

}
