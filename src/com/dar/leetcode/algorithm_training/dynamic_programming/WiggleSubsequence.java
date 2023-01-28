package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 376. 摆动序列 https://leetcode.cn/problems/wiggle-subsequence/description/
 * @create :2023-01-28 11:54:00
 */
public class WiggleSubsequence {

    public static void main(String[] args) {

    }

    public int wiggleMaxLength(int[] nums) {
        int up = 1;//最后两个数字递增，需要一个较小的数字
        int down = 1;//最后两个数字递减，需要一个较大的数字
        for (int i = 0; i <nums.length-1 ; i++) {
            if(nums[i+1]>nums[i]) up = down+1;
            if(nums[i+1]<nums[i]) down = up+1;
        }
        return Math.max(up,down);
    }
}
