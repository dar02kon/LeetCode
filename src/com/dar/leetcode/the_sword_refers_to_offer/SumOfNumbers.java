package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 64. 求1+2+…+n https://leetcode.cn/problems/qiu-12n-lcof/?favorite=xb9nqhhg
 * @create :2023-01-02 10:48:00
 */
public class SumOfNumbers {

    public static void main(String[] args) {
        int nums = new SumOfNumbers().sumNums(9);
        System.out.println(nums);
    }

    public int sumNums(int n) {
        boolean b = n > 0 && (n += sumNums(n - 1)) > 0;
        return n;
    }
}
