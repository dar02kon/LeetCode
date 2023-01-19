package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 77. 组合 https://leetcode.cn/problems/combinations/
 * @create :2023-01-19 15:50:00
 */
public class Combinations {

    public static void main(String[] args) {

    }


    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        combination(n, k, 1);
        return list;
    }

    public void combination(int n, int k, int index) {
        if (k == 0) {
            list.add(new ArrayList<>(record));
            return;
        }
        for (int i = index; i <= n - k + 1; i++) {//剪枝，保证搜索有意义
            record.add(i);
            combination(n, k - 1, i + 1);
            record.remove(record.size() - 1);
        }
    }
}
