package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 剑指 Offer 57 - II. 和为s的连续正数序列 https://leetcode.cn/problems/he-wei-sde-lian-xu-zheng-shu-xu-lie-lcof/?favorite=xb9nqhhg
 * @create :2022-12-28 19:48:00
 */
public class AndIsAContinuousSequenceOfPositiveNumbersForS {

    public static void main(String[] args) {
        int[][] continuousSequence = new AndIsAContinuousSequenceOfPositiveNumbersForS().findContinuousSequence(9);
        for (int[] ints : continuousSequence) {
            System.out.println(Arrays.toString(ints));
        }
    }

    public int[][] findContinuousSequence(int target) {
        List<int[]> list = new ArrayList<>();
        int left = 1,right = 2,num = 3;//num记录和
        while (left<right){
            if(num==target){//保存数据，从left到right
                int[] nums = new int[right-left+1];
                for (int i = left; i <=right ; i++) {
                    nums[i-left]=i;
                }
                list.add(nums);
            }
            if(num<=target){//小于或者等于目标值都移动右指针
                right++;
                num+=right;//更新和
            } else {//大于目标值移动左指针
                num-=left;//更新和，需要提前更新
                left++;
            }
        }
        return list.toArray(new int[list.size()][]);
    }
}
