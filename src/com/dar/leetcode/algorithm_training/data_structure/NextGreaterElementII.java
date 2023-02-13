package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author :wx
 * @description : 503. 下一个更大元素 II https://leetcode.cn/problems/next-greater-element-ii/description/
 * @create :2023-02-13 11:07:00
 */
public class NextGreaterElementII {

    public static void main(String[] args) {

    }

    public int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stack = new Stack<>();
        int[] result = new int[nums.length];
        Arrays.fill(result, -1);
        for (int i = 0; i < nums.length; i++) {
            while (!stack.isEmpty() && nums[i] > nums[stack.peek()]) {
                result[stack.pop()] = nums[i];
            }
            stack.push(i);
        }
        for (int num : nums) {
            while (result[stack.peek()] == -1 && num > nums[stack.peek()]) {
                result[stack.pop()] = num;
            }
        }
        return result;
    }

}
