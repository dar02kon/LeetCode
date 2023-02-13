package com.dar.leetcode.algorithm_training.data_structure;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author :wx
 * @description : 496. 下一个更大元素 I https://leetcode.cn/problems/next-greater-element-i/
 * @create :2023-02-13 10:29:00
 */
public class NextGreaterElementI {

    public static void main(String[] args) {

    }

    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Map<Integer,Integer> map = new HashMap<>();
        Stack<Integer> stack = new Stack<>();
        for (int num : nums2) {
            while (!stack.isEmpty() && num > stack.peek()) {
                map.put(stack.pop(), num);
            }
            stack.push(num);
        }
        int[] result = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            result[i]= map.getOrDefault(nums1[i], -1);
        }
        return result;
    }
}
