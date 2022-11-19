package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 16. 数值的整数次方 https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-19 12:55:00
 */
public class TheNumberToTheIntegerPower {
    public static void main(String[] args) {

    }

    public double myPow(double x, int n) {
        if(n==Integer.MIN_VALUE&&x>1){
            return 0;
        }
        if(n<0){
            x = 1/x;
            n = -n;
        }
        double result = 1.0;
        while (n>0){
            if((n&1)==1){
                result *= x;
            }
            x *= x;
            n >>= 1;
        }
        return result;
    }
}
