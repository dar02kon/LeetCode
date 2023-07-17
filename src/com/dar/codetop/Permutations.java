package com.dar.codetop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :wx
 * @description : 46. 全排列 https://leetcode.cn/problems/permutations/description/
 * @create :2023-07-17 22:13:00
 */
public class Permutations {
    // 存储结果
    List<List<Integer>> list = new ArrayList<>();
    // 存储中间过程的排列
    List<Integer> record = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        for (int num : nums) {
            record.add(num);
        }
        permutation(0, nums);
        return list;
    }

    /**
     * 大致思路是：确定当前位，拿剩余的元素依次去填充这一位
     * 通过index来标记当前位的位置，交换选定的元素到index，
     * index前的元素都是已经确定的，index后的元素都是还未使用的
     */
    private void permutation(int index, int[] nums) {
        if (index == nums.length) {
            list.add(new ArrayList<>(record));
        }
        for (int i = index; i < nums.length; i++) {
            Collections.swap(record, index, i);
            permutation(index + 1, nums);
            Collections.swap(record, index, i);
        }
    }
}
