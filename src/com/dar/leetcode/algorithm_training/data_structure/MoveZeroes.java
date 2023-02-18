package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 283. 移动零 https://leetcode.cn/problems/move-zeroes/
 * @create :2023-02-18 16:49:00
 */
public class MoveZeroes {

    public static void main(String[] args) {

    }

    public void moveZeroes(int[] nums) {
        int index = 0;
        for (int num : nums) {
            if(num!=-0) nums[index++] = num;
        }
        while (index<nums.length){
            nums[index++] = 0;
        }
    }
}
