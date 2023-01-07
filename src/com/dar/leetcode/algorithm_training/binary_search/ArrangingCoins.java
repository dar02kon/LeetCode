package com.dar.leetcode.algorithm_training.binary_search;

/**
 * @author :wx
 * @description : 441. 排列硬币 https://leetcode.cn/problems/arranging-coins/
 * @create :2023-01-07 10:02:00
 */
public class ArrangingCoins {

    public static void main(String[] args) {

    }

    public int arrangeCoins(int n) {
        int left = 1, right = n;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            long num = mid * (1L + mid) / 2;
            if (num > n) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }

}
