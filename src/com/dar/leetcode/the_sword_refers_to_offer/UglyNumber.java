package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * @author :wx
 * @description : 剑指 Offer 49. 丑数 https://leetcode.cn/problems/chou-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-27 10:47:00
 */
public class UglyNumber {

    public static void main(String[] args) {
        int i = new UglyNumber().nthUglyNumber2(10);
        System.out.println(i);
    }

    /**
     * 最小堆
     * @param n
     * @return
     */
    public int nthUglyNumber(int n) {
        Set<Long> set = new HashSet<>();
        int[] adds = new int[]{2,3,5};
        PriorityQueue<Long> priorityQueue = new PriorityQueue<>();
        set.add(1L);
        priorityQueue.offer(1L);
        int result = 0;
        for (int i = 0; i < n; i++) {
            long poll = priorityQueue.poll();
            result = (int)poll;
            for (int add : adds) {
                Long num = add*poll;
                if(!set.contains(num)){
                    set.add(num);
                    priorityQueue.add(num);
                }
            }
        }
        return result;
    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int nthUglyNumber2(int n) {
        int[] dp = new int[n];
        dp[0] = 1;
        int p1 = 0,p2=0,p3=0;
        for (int i = 1; i <n ; i++) {
            int num1 = dp[p1]*2,num2 = dp[p2]*3,num3 = dp[p3]*5;
            int num = Math.min(Math.min(num1,num2),num3);
            dp[i]=num;
            if(num1==dp[i]){
                p1++;
            }
            if(num2==dp[i]){
                p2++;
            }
            if(num3==dp[i]){
                p3++;
            }
        }
        return dp[n-1];
    }
}
