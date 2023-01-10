package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 167. 两数之和 II - 输入有序数组 https://leetcode.cn/problems/two-sum-ii-input-array-is-sorted/
 * @create :2023-01-10 08:49:00
 */
public class TwoSumIIInputArrayIsSorted {

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] numbers, int target) {
        int left = 0,right = numbers.length-1;
        while (left<right){
            int sum = numbers[left]+numbers[right];
            if(sum==target){
                return new int[]{left+1,right+1};
            } else if(sum<target){
                left++;
            } else {
                right--;
            }
        }
        return new int[]{};
    }
}
