package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description :  全排列 II  https://leetcode.cn/problems/permutations-ii/description/
 * @create :2022-11-05 13:16:00
 */
public class PermutationsIi {
    public static void main(String[] args) {

    }
    boolean[] flag;
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();

    /**
     * 递归回溯
     * 数组中存在重复元素，需要注意去重，添加过的元素就没必要再次添加（标记的是位置即下标，不能标记元素的大小）
     * 可以设置标记数组辅助记忆哪些数字已经添加过(添加标记为true，删除重新标记为false)
     * 可以将数组进行排序，相同的元素就会出现在一起，通过标记数组flag[i]来判断当前元素是否已经添加过，
     * 以及i > 0 && nums[i] == nums[i - 1] && !flag[i - 1]来判断相同的元素是否已经添加过
     * <p>
     *     例如 [1 1 2 3]
     *     我们将第一个元素1打头进行排序后，再对第二个1打头进行排序是没有意义的,肯定会重复
     * </p>
     * @param nums
     * @return
     */
    public List<List<Integer>> permuteUnique(int[] nums) {
        flag = new boolean[nums.length];//初始化数组
        Arrays.sort(nums);//排序
        backtrack(nums, 0);
        return list;
    }

    public void backtrack(int[] nums, int index) {
        if (index == nums.length) {//满员添加到返回列表
            list.add(new ArrayList<Integer>(record));
            return;
        }
        for (int i = 0; i < nums.length; ++i) {
            if (flag[i] || (i > 0 && nums[i] == nums[i - 1] && !flag[i - 1])) {//去重
                continue;
            }
            record.add(nums[i]);//添加
            flag[i] = true;//标记
            backtrack(nums, index + 1);//继续递归遍历（以下一个数字打头）
            flag[i] = false;//删除
            record.remove(index);//取消标记
        }
    }
    

}
