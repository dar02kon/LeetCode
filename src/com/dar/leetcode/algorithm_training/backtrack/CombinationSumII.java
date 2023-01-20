package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 40. 组合总和 II https://leetcode.cn/problems/combination-sum-ii/
 * @create :2023-01-20 09:41:00
 */
public class CombinationSumII {

    public static void main(String[] args) {

    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();
    boolean[] visited;

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        visited = new boolean[candidates.length];
        Arrays.sort(candidates);
        combination(0, candidates, target);
        return list;
    }

    /**
     * 使用标记数组
     *
     * @param index
     * @param candidates
     * @param target
     */
    public void combination(int index, int[] candidates, int target) {
        if (target <= 0) {
            if (target == 0) {
                list.add(new ArrayList<>(record));
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            if (i > 0 && candidates[i] == candidates[i - 1] && !visited[i - 1]) {//去重
                continue;
            }
            record.add(candidates[i]);
            visited[i] = true;
            combination(i + 1, candidates, target - candidates[i]);
            record.remove(record.size() - 1);
            visited[i] = false;
        }
    }

    /**
     * 不使用标记数组
     *
     * @param index
     * @param candidates
     * @param target
     */
    public void combination2(int index, int[] candidates, int target) {
        if (target <= 0) {
            if (target == 0) {
                list.add(new ArrayList<>(record));
            }
            return;
        }
        for (int i = index; i < candidates.length; i++) {
            record.add(candidates[i]);
            combination2(i + 1, candidates, target - candidates[i]);
            record.remove(record.size() - 1);

            while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {//已经删除的元素没有必要再次添加
                i++;
            }
        }
    }
}
