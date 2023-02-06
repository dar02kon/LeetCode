package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 303. 区域和检索 - 数组不可变 https://leetcode.cn/problems/range-sum-query-immutable/description/
 * @create :2023-02-06 10:07:00
 */
public class RangeSumQueryImmutable {

    public static void main(String[] args) {

    }


}

class NumArray {
    int[] sum;

    public NumArray(int[] nums) {
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i - 1] + nums[i];
        }
        this.sum = nums;
    }

    public int sumRange(int left, int right) {
        return left == 0 ? sum[right] : sum[right] - sum[left - 1];
    }
}