package com.dar.codetop;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 1. 两数之和 https://leetcode.cn/problems/two-sum/description/
 * @create :2023-07-19 21:16:00
 */
public class TwoSum {
    /**
     * 要返回对应的下标，不能排序后用双指针去寻找
     * 可以用哈希表来存储值与位置的映射关系，从两头往中间找
     * 一边存一边找
     */
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            if (map.containsKey(target - nums[left])) {
                return new int[]{left, map.get(target - nums[left])};
            } else {
                map.put(nums[left], left);
            }
            if (map.containsKey(target - nums[right])) {
                return new int[]{right, map.get(target - nums[right])};
            } else {
                map.put(nums[right], right);
            }
            left++;
            right--;
        }
        return null;
    }
}
