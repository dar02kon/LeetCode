package com.dar.leetcode.algorithm_training.sort;

import java.util.*;

/**
 * @author :wx
 * @description : 347. 前 K 个高频元素 https://leetcode.cn/problems/top-k-frequent-elements/description/
 * @create :2023-01-13 10:12:00
 */
public class TopKFrequentElements {

    public static void main(String[] args) {
        int[] nums = new int[]{1,1,1,2,2,3};
        int[] ints = new TopKFrequentElements().topKFrequent(nums, 2);
        System.out.println(Arrays.toString(ints));
    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums) {//统计数字出现的概率
            map.put(num,map.getOrDefault(num,0)+1);
        }
        //设置桶的个数
        List<Integer>[] bucket = new List[nums.length+1];
        for (Integer num : map.keySet()) {
            int count = map.get(num);//获取该元素对应的个数为下标值
            if(bucket[count]==null){
                bucket[count] = new ArrayList<>();
            }
            bucket[count].add(num);
        }
        //下标即代表对应元素的个数，从后往前取k个即可
        int[] result = new int[k];
        for(int i = bucket.length - 1; i >= 0 && k>0; i--) {
            if(bucket[i] != null) {
                for (Integer num : bucket[i]) {
                    result[--k] = num;
                }
            }
        }
        return result;
    }
}
