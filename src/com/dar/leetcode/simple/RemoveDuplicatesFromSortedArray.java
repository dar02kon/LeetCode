package com.dar.leetcode.simple;

import java.util.*;

/**
 * @author :wx
 * @description :  删除有序数组中的重复项  https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 * @create :2022-09-21 19:35:00
 */
public class RemoveDuplicatesFromSortedArray {
    public static void main(String[] args) {
        int[] nums = {-3,-1,0,0,0,3,3};
        System.out.println(new RemoveDuplicatesFromSortedArray().removeDuplicates2(nums));
    }

    /**
     * 直接使用Set集合来帮助我们完成去重和排序（但速度慢）
     * HashSet不会按照我们预想的顺序去添加数据
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        Set<Integer> set = new HashSet<>();
        if(nums.length<=1){
            return nums.length;
        }
        for (int num : nums) {
            set.add(num);
        }
        TreeSet<Integer> treeSet = new TreeSet<>(set);
        int i = 0;
        for (Integer integer : treeSet) {
            nums[i++] = integer;
        }
        return set.size();
    }

    /**
     * 第二种思路：
     * 设置两个变量记录start，end记录数组更新位置和遍历位置
     * 如果nums[start]!=nums[end]且start与end不相邻，则将nums[end]赋值给start所指的下一个位置
     * 每次循环都需要end++
     * @param nums
     * @return
     */
    public int removeDuplicates2(int[] nums) {
        if(nums.length<=1){
            return nums.length;
        }
        int start = 0;
        int end = 1;
        while (end< nums.length){
            if(nums[start]!=nums[end]){
                if(start+1!=end)
                    nums[++start] = nums[end];
                else
                    start++;
            }
            end++;
        }
        return start+1;
    }
}
