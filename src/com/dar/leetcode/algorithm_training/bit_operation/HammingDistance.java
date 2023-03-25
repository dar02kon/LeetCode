package com.dar.leetcode.algorithm_training.bit_operation;

/**
 * @author :wx
 * @description : 461. 汉明距离 https://leetcode.cn/problems/hamming-distance/
 * @create :2023-03-25 13:19:00
 */
public class HammingDistance {

    public static void main(String[] args) {

    }

    public int hammingDistance(int x, int y) {
        // 异或
        int num = x ^ y;
        int count = 0;
        while (num != 0) {
            if ((num & 1) == 1) { // 统计1的个数
                count++;
            }
            num >>= 1;
        }
        return count;
    }

}
