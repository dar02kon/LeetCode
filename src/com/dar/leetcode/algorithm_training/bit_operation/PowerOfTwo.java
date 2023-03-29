package com.dar.leetcode.algorithm_training.bit_operation;

/**
 * @author :wx
 * @description :
 * @create :2023-03-29 15:53:00
 */
public class PowerOfTwo {

    public static void main(String[] args) {

    }

    public boolean isPowerOfTwo(int n) {
        int i = 1;
        while (i > 0) {
            if (i == n) {
                return true;
            }
            i <<= 1;
        }
        return false;
    }

    public boolean isPowerOfTwo2(int n) {
        return n>0&&(n&(n-1))==0;
    }
}
