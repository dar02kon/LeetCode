package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 10- II. 青蛙跳台阶问题 https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof/?favorite=xb9nqhhg
 * @create :2022-11-13 13:09:00
 */
public class FrogJumpingSteps {

    public static void main(String[] args) {
        int i = new FrogJumpingSteps().numWays2(7);
        System.out.println(i);
    }

    static final int MOD = 1000000007;

    /**
     * 常规做法
     * @param n
     * @return
     */
    public int numWays(int n) {
        if (n <= 1)
            return 1;
        int a = 1;
        int b = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % MOD;
            a = b;
            b = sum;
        }
        return b;
    }

    /**
     * 矩阵快速幂
     * @param n
     * @return
     */
    public int numWays2(int n) {
        int[][] nums = {{1, 1}, {1, 0}};
        int[][] pow = pow(nums, n - 1);
        System.out.println(Arrays.deepToString(pow));
        return (pow[0][0] + pow[0][1]) % MOD;
    }

    //矩阵快速幂
    public int[][] pow(int[][] nums, int n) {
        int[][] sum = {{1, 0}, {0, 1}};//初始化，使用{{1,0},{1,0}}也可以，结果的第一行是相同的
        while (n > 0) {
            if ((n & 1) == 1) {
                sum = multiply(sum, nums);
            }
            nums = multiply(nums, nums);
            n >>= 1;
        }
        return sum;
    }
    //矩阵乘法
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                c[i][j] = (int) (((long) a[i][0] * b[0][j] + (long) a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }
}
