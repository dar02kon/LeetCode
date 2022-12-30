package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 62. 圆圈中最后剩下的数字 https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-30 21:03:00
 */
public class TheLastNumberLeftInTheCircle {

    public static void main(String[] args) {

    }

    public int lastRemaining(int n, int m) {
        int result = 0;
        for (int i = 2; i != n + 1; ++i) {
            result = (m + result) % i;
        }
        return result;
    }
}

