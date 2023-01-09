package com.dar.leetcode.algorithm_training.greedy_thinking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author :wx
 * @description : 406. 根据身高重建队列 https://leetcode.cn/problems/queue-reconstruction-by-height/description/
 * @create :2023-01-09 11:11:00
 */
public class QueueReconstructionByHeight {

    public static void main(String[] args) {

    }

    public int[][] reconstructQueue(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if(a[0]==b[0]) return a[1]-b[1];
                return b[0]-a[0];
            }
        });
        List<int[]> list = new ArrayList<>();
        for (int[] person : people) {
            list.add(person[1], new int[]{person[0], person[1]});
        }
        for (int i = 0; i <people.length ; i++) {
            people[i][0] = list.get(i)[0];
            people[i][1] = list.get(i)[1];
        }
        return people;
        
    }


}
