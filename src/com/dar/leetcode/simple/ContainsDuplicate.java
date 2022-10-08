package com.dar.leetcode.simple;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author :wx
 * @description : 存在重复元素 https://leetcode.cn/problems/contains-duplicate/
 * @create :2022-10-08 18:51:00
 */
public class ContainsDuplicate {

    public static void main(String[] args) {
        int[] nums = {1,2,3,4,5};
        System.out.println(new ContainsDuplicate().containsDuplicate2(nums));
    }

    /**
     * 常规思路：
     * 使用Hash表来存储已经出现过的数字
     * 遍历数组进行比较
     * @param nums
     * @return
     */
    public boolean containsDuplicate(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            if(set.contains(num)){
                return true;
            } else {
                set.add(num);
            }
        }
        return false;
    }

    /**
     * 排序后比较
     * @param nums
     * @return
     */
    public boolean containsDuplicate2(int[] nums) {
        if(nums.length<=1){
            return false;
        }
        Arrays.sort(nums);
        int result = nums[0];
        /**
         *         int n = nums.length;
         *         for (int i = 0; i < n - 1; i++) {
         *             if (nums[i] == nums[i + 1]) {
         *                 return true;
         *             }
         *         }
         *         return false;
         */
        for (int i = 1; i < nums.length; i++) {
            result ^= nums[i];
            if(result==0){
                return true;
            } else {
                result = nums[i];
            }
        }
        return false;
    }

}
