package com.dar.leetcode.algorithm_training.greedy_thinking;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author :wx
 * @description : 452. 用最少数量的箭引爆气球 https://leetcode.cn/problems/minimum-number-of-arrows-to-burst-balloons/description/
 * @create :2023-01-08 09:48:00
 */
public class MinimumNumberOfArrowsToBurstBalloons {

    public static void main(String[] args) {

    }

    public int findMinArrowShots(int[][] points) {
        if(points.length==0) return 0;
        Arrays.sort(points, Comparator.comparingInt(a -> a[1]));//根据右边界排序
        int boundary = points[0][1];//设置临界点，第一个数组的右边界
        int count = 1;//第一个飞镖
        for (int i = 1; i < points.length ; i++) {
            if(points[i][0]<=boundary) continue;//存在公共区域，同一个飞镖就可以打破气球
            //不存在公共区域
            boundary = points[i][1];//更新第一个数组右边界
            count++;//换一个飞镖
        }
        return count;
    }
}
