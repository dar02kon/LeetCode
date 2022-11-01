package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 组合总和 II https://leetcode.cn/problems/combination-sum-ii/
 * @create :2022-11-01 19:07:00
 */
public class CombinationSumIi {
    public static void main(String[] args) {
        int[] nums = {10, 1, 2, 7, 6, 1, 5};
        List<List<Integer>> list = new CombinationSumIi().combinationSum2(nums, 8);
        for (List<Integer> integerList : list) {
            System.out.println(integerList);
        }
    }

    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();


    /**
     * 找到所有的组合并不难，递归回溯就可以找到
     * 关键是去重，可以先将数组进行排序，回溯移除集合元素后循环，判断下一个要添加的元素是否与移除的相同，相同则继续循环
     * 提前将目标值减去当前元素，如果小于0则直接跳出循环，因为后面的元素都比前面的大，已经不可能满足条件了
     *
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);//排序
        getRecord(candidates, target, 0);
        return list;
    }

    public void getRecord(int[] candidates, int target, int index) {
        if (target == 0) {//找到了，添加集合
            list.add(new ArrayList<>(record));
        }
        if (index != candidates.length) {//没有越界
            for (int i = index; i < candidates.length; ++i) {
                int num = target - candidates[i];
                if (num < 0) {//小于0跳出循环
                    break;
                }
                record.add(candidates[i]);//添加元素到集合
                getRecord(candidates, num, i + 1);//继续遍历，i+1是因为每个数字在每个组合中只能使用 一次
                record.remove(record.size() - 1);//移除元素，防止对接下来的循环产生影响
                while (i < candidates.length - 1 && candidates[i] == candidates[i + 1]) {//去重
                    ++i;
                }
            }

        }
    }


}
