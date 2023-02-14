package com.dar.leetcode.algorithm_training.data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 594. 最长和谐子序列 https://leetcode.cn/problems/longest-harmonious-subsequence/
 * @create :2023-02-14 10:56:00
 */
public class LongestHarmoniousSubsequence {

    public static void main(String[] args) {

    }

    public int findLHS(int[] nums) {
        Map<Integer,Integer> map = new HashMap<>();
        int max = 0;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            if(map.containsKey(num-1)){
                max = Math.max(count+1+map.get(num-1),max);
            }
            if(map.containsKey(num+1)){
                max = Math.max(count+1+map.get(num+1),max);
            }
            map.put(num,count+1);
        }
        return max;
    }
}
