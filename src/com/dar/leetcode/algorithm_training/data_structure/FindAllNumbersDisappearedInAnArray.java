package com.dar.leetcode.algorithm_training.data_structure;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 448. 找到所有数组中消失的数字 https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 * @create :2023-02-20 08:38:00
 */
public class FindAllNumbersDisappearedInAnArray {

    public static void main(String[] args) {

    }

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != i + 1 && nums[i] != nums[nums[i] - 1]) {
                int temp = nums[nums[i] - 1];
                nums[nums[i] - 1] = nums[i];
                nums[i] = temp;
            }
        }

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != i + 1) {
                list.add(i+1);
            }
        }
        return list;
    }
}
