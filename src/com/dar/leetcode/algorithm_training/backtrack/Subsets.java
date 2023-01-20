package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 78. 子集 https://leetcode.cn/problems/subsets/description/
 * @create :2023-01-20 11:21:00
 */
public class Subsets {

    public static void main(String[] args) {

    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    public List<List<Integer>> subsets(int[] nums) {
        combination(nums, 0);
        return list;
    }

    public void combination(int[] nums, int index) {
        list.add(new ArrayList<>(record));//提前添加
        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            record.add(nums[i]);
            combination(nums, i + 1);
            record.remove(record.size() - 1);
        }
    }
}
