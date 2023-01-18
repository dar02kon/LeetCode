package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author :wx
 * @description : 46. 全排列 https://leetcode.cn/problems/permutations/description/
 * @create :2023-01-18 09:46:00
 */
public class Permutations {

    public static void main(String[] args) {

    }
    boolean[] visited;//标记数组
    List<List<Integer>> list = new ArrayList<>();
    List<Integer> record = new ArrayList<>();
    public List<List<Integer>> permute(int[] nums) {
        visited = new boolean[nums.length];
        combination(nums);
        return list;
    }

    public void combination(int[] nums){
        if(record.size()==nums.length){
            list.add(new ArrayList<>(record));
        }
        for (int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;//已经访问，跳过
            record.add(nums[i]);//添加数字
            visited[i] = true;//标记
            combination(nums);//继续递归添加
            record.remove(record.size()-1);//删除添加的数字
            visited[i] = false;//取消标记
        }
    }


    public List<List<Integer>> permute2(int[] nums) {
        for (int num : nums) {
            record.add(num);
        }
        combination2(0,nums);
        return list;
    }

    public void combination2(int index,int[] nums){
       if(index==nums.length){
           list.add(new ArrayList<>(record));
       }
        for (int i = index; i <nums.length ; i++) {
            Collections.swap(record,i,index);//交换
            combination2(index+1,nums);
            Collections.swap(record,i,index);//撤销交换
        }
    }
}
