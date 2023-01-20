package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 90. 子集 II https://leetcode.cn/problems/subsets-ii/
 * @create :2023-01-20 11:33:00
 */
public class SubsetsII {

    public static void main(String[] args) {

    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        combination(nums, 0);
        return list;
    }

    public void combination(int[] nums, int index) {
        list.add(new ArrayList<>(record));
        if (index == nums.length) return;
        for (int i = index; i < nums.length; i++) {
            record.add(nums[i]);
            combination(nums, i + 1);
            record.remove(record.size() - 1);
            while (i < nums.length - 1 && nums[i] == nums[i + 1]) {//已经删除的元素没有必要再次添加
                i++;
            }
        }
    }

}
