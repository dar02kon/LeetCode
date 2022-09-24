package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 爬楼梯 https://leetcode.cn/problems/climbing-stairs/
 * @create :2022-09-24 19:44:00
 */
public class ClimbingStairs {

    public static void main(String[] args) {
        System.out.println(new ClimbingStairs().climbStairs2(1));
    }

    /**
     * 思路：
     * 到达n层楼梯前只有两中可能（假定n>=2且爬0层楼梯只有一种方式）：
     * 一是站在n-1层往上爬一个台阶
     * 二是站在n-2层网上爬两个台阶
     * 以此类推
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        int[] num = new int[n+1];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i < n+1 ; i++) {
            num[i] = num[i-1] + num[i-2];
        }
        return num[n];
    }

    /**
     * 稍微优化一下：
     * 可以看出n阶楼梯有多少中爬法只与n-1阶楼梯，n-2阶楼梯有多少种爬法有关
     * 所以只需要三个变量，一个保存n阶楼梯的爬法，一个保存n-1阶楼梯的爬法，一个保存n-2阶楼梯的爬法
     * 循环更换数据
     * 如果n<=1，则直接返回1，否则返回num[2]
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int[] num = new int[3];
        num[0] = 1;
        num[1] = 1;
        for (int i = 2; i < n+1 ; i++) {
            num[2] = num[0]+num[1];
            num[0] = num[1];
            num[1] = num[2];
        }
        return n<=1 ? num[n] : num[2];
    }
}
