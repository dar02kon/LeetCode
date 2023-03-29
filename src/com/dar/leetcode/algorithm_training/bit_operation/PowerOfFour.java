package com.dar.leetcode.algorithm_training.bit_operation;

/**
 * @author :wx
 * @description : 342. 4的幂 https://leetcode.cn/problems/power-of-four/description/
 * @create :2023-03-29 15:21:00
 */
public class PowerOfFour {

    public static void main(String[] args) {

    }

    public boolean isPowerOfFour(int n) {
        int i = 1;
        while (i > 0) {
            if (i == n) {
                return true;
            }
            i <<= 2;
        }
        return false;
    }
}
