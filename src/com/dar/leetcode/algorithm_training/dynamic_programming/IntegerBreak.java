package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 343. 整数拆分 https://leetcode.cn/problems/integer-break/description/
 * @create :2023-01-24 14:18:00
 */
public class IntegerBreak {

    public static void main(String[] args) {

    }

    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j <= i / 2; j++) {//将i拆分成 j 与 i-j
                // j * (i - j) 表示不对 i-j 进行拆分的乘积，
                // j * dp[i - j] 表示对 i- j进行拆分
                // 取上面结果的最大值与 dp[i] 进行比较，根据 j 的取值，dp[i]会有多种结果，取其中的最大值
                dp[i] = Math.max(dp[i], Math.max((j * (i - j)), j * dp[i - j]));
            }
        }
        return dp[n];
    }
}
