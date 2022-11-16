package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 14- I. 剪绳子 https://leetcode.cn/problems/jian-sheng-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-16 19:24:00
 */
public class CutTheRope {

    public static void main(String[] args) {

    }

    /**
     * 动态规划
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        int[] dp = new int[n+1];
        for (int i = 2; i <= n ; i++) {
            int max = 0;
            for (int j = 1; j <= i/2; j++) {
                max = Math.max(max,Math.max(j*(i-j),j*dp[i-j]));
            }
            dp[i] = max;
        }
        return dp[n];
    }

    /**
     * 数学规律
     * @param n
     * @return
     */
    public int cuttingRope2(int n) {
        if(n < 4){
            return n - 1;
        }
        int res = 1;
        while(n > 4){
            res *= 3;
            n -= 3;
        }
        return res * n;
    }

    /**
     * 数学规律
     * @param n
     * @return
     */
    public int cuttingRope3(int n) {
        if(n < 4){
            return n - 1;
        }
        int a = n/3;
        int b = n%3;
        if(b==1){
            return (int) (Math.pow(3,a-1)*4);
        } else if (b==2){
            return (int) (Math.pow(3,a)*b);
        } else {
            return (int) (Math.pow(3,a));
        }
    }


}
