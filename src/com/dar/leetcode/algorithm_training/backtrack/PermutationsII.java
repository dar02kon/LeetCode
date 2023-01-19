package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 47. 全排列 II https://leetcode.cn/problems/permutations-ii/
 * @create :2023-01-19 15:29:00
 */
public class PermutationsII {



    public static void main(String[] args) {

    }
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();
    boolean[] visited;
    public List<List<Integer>> permuteUnique(int[] nums) {
        visited = new boolean[nums.length];
        Arrays.sort(nums);
        combination(nums);
        return list;
    }

    public void combination(int[] nums) {
        if (record.size() == nums.length) {
            list.add(new ArrayList<>(record));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i] || (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1])) {
                continue;
            }
            visited[i] = true;
            record.add(nums[i]);
            combination(nums);
            visited[i] = false;
            record.remove(record.size() - 1);
        }
    }
}
