package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description :
 * @create :2022-11-12 18:35:00
 */
public class TheFibonacciSequence {
    public static void main(String[] args) {
        int fib = new TheFibonacciSequence().fib2(95);
        System.out.println(fib);
    }

    /**
     * 常规做法
     * @param n
     * @return
     */
    public int fib(int n) {
        if (n <= 1)
            return n;
        int a = 0;
        int b = 1;
        int sum;
        for (int i = 2; i <= n; i++) {
            sum = (a + b) % 1000000007;//对结果取模
            a = b;
            b = sum;
        }
        return b;
    }

    static final int MOD = (int) (1e9) + 7;

    public int fib2(int n) {
        if (n <= 1)
            return n;
        int[][] num = {{1, 1}, {1, 0}};
        int[][] pow = pow(num, n - 1);
        return pow[0][0];
    }

    // 矩阵快速幂算法
    public int[][] pow(int[][] a, int n) {
        int[][] sum = {{1, 0}, {1, 0}};//初始化，相当于普通数字1
        while (n > 0) {
            if ((n & 1) == 1) {
                sum = multiply(sum, a);
            }
            a = multiply(a, a);
            n >>= 1;
        }
        return sum;
    }

    // 矩阵乘法
    public int[][] multiply(int[][] a, int[][] b) {
        int[][] result = new int[2][2];
        for (int i = 0; i < a.length; i++) {
            for (int j = 0; j < b.length; j++) {
                result[i][j] = (int) ((((long) a[i][0] * b[0][j]) + (long) a[i][1] * b[1][j]) % MOD);//a的一行乘以b的一列
            }
        }
        return result;
    }

}
