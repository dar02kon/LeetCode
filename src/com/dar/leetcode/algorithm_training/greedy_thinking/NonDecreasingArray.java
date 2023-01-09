package com.dar.leetcode.algorithm_training.greedy_thinking;

/**
 * @author :wx
 * @description : 665. 非递减数列 https://leetcode.cn/problems/non-decreasing-array/
 * @create :2023-01-09 09:01:00
 */
public class NonDecreasingArray {

    public static void main(String[] args) {

    }

    public boolean checkPossibility(int[] nums) {
        int count = 1;
        for (int i = 0; i < nums.length-1; i++) {
            if(nums[i]>nums[i+1]){
                count--;
                if(i>0&&nums[i+1]<nums[i-1]){
                    nums[i+1]=nums[i];
                } else {
                    nums[i] = nums[i+1];
                }
            }

        }
        return count>=0;
    }
}
