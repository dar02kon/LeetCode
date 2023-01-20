package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 39. 组合总和 https://leetcode.cn/problems/combination-sum/description/
 * @create :2023-01-20 09:13:00
 */
public class CombinationSum {

    public static void main(String[] args) {

    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        combination(0, candidates, target);
        return list;
    }

    public void combination(int index, int[] candidates, int target) {
        if (target <= 0) {
            if (target == 0) {
                list.add(new ArrayList<>(record));
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            record.add(candidates[i]);
            combination(i, candidates, target - candidates[i]);//可以重复添加自己
            record.remove(record.size() - 1);
        }
    }
}
