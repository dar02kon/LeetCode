package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description :  442. 数组中重复的数据 https://leetcode.cn/problems/find-all-duplicates-in-an-array/
 * @create :2023-02-20 08:56:00
 */
public class FindAllDuplicatesInAnArray {

    public static void main(String[] args) {

    }

    public List<Integer> findDuplicates(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i]!=i+1&&nums[i]!=nums[nums[i]-1]){
                int temp = nums[nums[i]-1];
                nums[nums[i]-1] = nums[i];
                nums[i] = temp;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if(nums[i]!=i+1){
                list.add(nums[i]);
            }
        }
        return list;
    }
}
