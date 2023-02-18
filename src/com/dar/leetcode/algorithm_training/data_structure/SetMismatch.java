package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 645. 错误的集合 https://leetcode.cn/problems/set-mismatch/description/
 * @create :2023-02-18 17:02:00
 */
public class SetMismatch {

    public static void main(String[] args) {

    }

    public int[] findErrorNums(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                return new int[]{nums[i], i + 1};
            }
        }
        return null;
    }

    // 交换
    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}
