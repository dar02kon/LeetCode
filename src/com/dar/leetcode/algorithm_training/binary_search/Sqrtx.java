package com.dar.leetcode.algorithm_training.binary_search;

/**
 * @author :wx
 * @description : x 的平方根  https://leetcode.cn/problems/sqrtx/
 * @create :2023-01-07 09:08:00
 */
public class Sqrtx {

    public static void main(String[] args) {

    }

    public int mySqrt(int x) {
        if (x < 1) return x;
        int left = 1, right = x;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int num = x / mid;
            if (mid > num) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
