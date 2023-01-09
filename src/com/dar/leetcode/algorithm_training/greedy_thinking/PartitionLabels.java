package com.dar.leetcode.algorithm_training.greedy_thinking;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 763. 划分字母区间 https://leetcode.cn/problems/partition-labels/
 * @create :2023-01-09 10:23:00
 */
public class PartitionLabels {

    public static void main(String[] args) {
        List<Integer> list = new PartitionLabels().partitionLabels2("abcd");
        System.out.println(list);
    }

    public List<Integer> partitionLabels(String s) {
        List<Integer> list = new ArrayList<>();
        int i = 0;
        while (i<s.length()){
            int j = s.lastIndexOf(s.charAt(i));//开始字符最后出现的位置
            for (int k = i+1; k < j; k++) {
                j = Math.max(j,s.lastIndexOf(s.charAt(k)));//选取更大的右边界
            }
            list.add(j-i+1);
            i = j+1;
        }
        return list;
    }

    public List<Integer> partitionLabels2(String s) {
        List<Integer> list = new ArrayList<>();
        int[] last = new int[26];
        for (int i = 0; i < 26 ; i++) {//提前记录
            last[i] = s.lastIndexOf((char)(i+'a'));
        }
        int i=0;
        while (i<s.length()){
            int j = last[s.charAt(i)-'a'];
            for (int k = i+1; k < j; k++) {
                j = Math.max(j,last[s.charAt(k)-'a']);
            }
            list.add(j-i+1);
            i = j+1;
        }
        return list;
    }
}
