package com.dar.leetcode.the_sword_refers_to_offer;


/**
 * @author :wx
 * @description : 剑指 Offer 43. 1～n 整数中 1 出现的次数 https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof/description/
 * @create :2022-12-11 09:57:00
 */
public class NumberOfTimesThatOneOccursInTheIntegerFromOneToN {



    public static void main(String[] args) {
        int i = new NumberOfTimesThatOneOccursInTheIntegerFromOneToN().countDigitOne(12);
        System.out.println(i);
    }
    long[] dp;
    public int countDigitOne(int n) {
        dp = new long[11];
        dp[0]=0;
        dp[1]=1;
        for (int i = 2; i < 10; i++) {
            dp[i] = (long) (dp[i-1]*10+Math.pow(10,i-1));
        }
        int digits = 0;
        int result = 0;
        int pre = 0;
        while (n>0){
            digits++;
            result+=count(n%10,digits,pre);
            pre += n%10*Math.pow(10,digits-1);
            n/=10;
        }
        return result;
    }

    public int count(int num,int n,int pre){
        int result = (int) (num*dp[n-1]);
        if(num>1){
            result+=Math.pow(10,n-1);
        } else if(num==1){
            result+=pre+1;
        }
        return result;
    }
}
