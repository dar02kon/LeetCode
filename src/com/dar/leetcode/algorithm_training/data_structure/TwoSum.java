package com.dar.leetcode.algorithm_training.data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 1. 两数之和 https://leetcode.cn/problems/two-sum/
 * @create :2023-02-14 10:28:00
 */
public class TwoSum {

    public static void main(String[] args) {

    }

    public int[] twoSum(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(map.containsKey(target-nums[i])) {
                return new int[]{map.get(target-nums[i]),i};
            } else {
                map.put(nums[i],i);
            }
        }
        return null;
    }

    public int[] twoSum2(int[] nums, int target) {
        Map<Integer,Integer> map = new HashMap<>();
        int left = 0,right = nums.length-1;
        while (left<=right){
            int l = target-nums[left];
            int r = target-nums[right];
            if(map.containsKey(l)){
                return new int[]{map.get(l),left};
            } else {
                map.put(nums[left],left++);
            }

            if(map.containsKey(r)){
                return new int[]{map.get(r),right};
            } else {
                map.put(nums[right],right--);
            }
        }
        return null;
    }

}
