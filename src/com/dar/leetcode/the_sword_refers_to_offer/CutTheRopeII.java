package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 14- II. 剪绳子 II https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/?favorite=xb9nqhhg
 * @create :2022-11-17 19:12:00
 */
public class CutTheRopeII {

    public static void main(String[] args) {
        int i = new CutTheRopeII().cuttingRope(120);
        System.out.println(i);
    }

    public int cuttingRope(int n) {
        if (n < 4) {
            return n - 1;
        }
        long result = 1;
        while (n > 4) {
            result = result * 3 % 1000000007;
            n -= 3;
        }
        return (int) (result * n % 1000000007);
    }


}
