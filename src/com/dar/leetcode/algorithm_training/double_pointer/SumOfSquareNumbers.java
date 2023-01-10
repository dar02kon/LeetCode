package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 633. 平方数之和 https://leetcode.cn/problems/sum-of-square-numbers/description/
 * @create :2023-01-10 09:54:00
 */
public class SumOfSquareNumbers {

    public static void main(String[] args) {

    }

    public boolean judgeSquareSum(int c) {
        int left = 0, right = (int) Math.sqrt(c);
        while (left <= right) {
            int target = c - left * left;//使用减法来代替加法，避免越界
            if (target == right * right) {
                return true;
            } else if (target < right * right) {
                right--;
            } else {
                left++;
            }
        }
        return false;
    }
}
