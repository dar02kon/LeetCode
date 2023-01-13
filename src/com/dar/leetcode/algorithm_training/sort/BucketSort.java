package com.dar.leetcode.algorithm_training.sort;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 桶排序
 * @create :2023-01-13 09:41:00
 */
public class BucketSort {

    //获取数组最大值和最小值
    private static int[] getMinAndMax(List<Integer> arr){
        int max = arr.get(0);
        int min = arr.get(0);
        for (Integer num : arr) {
            max = Math.max(num,max);
            min = Math.min(num,min);
        }
        return new int[]{min,max};
    }

    public static List<Integer> bucketSort(List<Integer> arr, int bucket_size){
        if (arr.size() < 2 || bucket_size == 0) {
            return arr;
        }
        int[] minAndMax = getMinAndMax(arr);
        int min = minAndMax[0];
        int max = minAndMax[1];
        //确定桶的个数
        int bucket_count = (max-min)/bucket_size + 1;
        List<List<Integer>> buckets = new ArrayList<>();
        for (int i = 0; i < bucket_count; i++) {
            buckets.add(new ArrayList<Integer>());
        }

        //确定数组中元素所在桶中的位置
        for (Integer num : arr) {
            int index = (num-min)/bucket_size;
            buckets.get(index).add(num);
        }

        //对桶中的元素进行排序
        for (int i = 0; i < buckets.size(); i++) {
            if (buckets.get(i).size() > 1) {
                buckets.set(i, bucketSort(buckets.get(i), bucket_size / 2));
            }
        }

        //添加结果
        ArrayList<Integer> result = new ArrayList<>();
        for (List<Integer> bucket : buckets) {
            result.addAll(bucket);
        }
        return result;
    }

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
       list.add(1);
       list.add(6);
       list.add(3);
       list.add(9);
        List<Integer> sort = bucketSort(list, 3);
        System.out.println(sort);
    }
}
