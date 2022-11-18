package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 15. 二进制中1的个数 https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/description/
 * @create :2022-11-18 16:53:00
 */
public class TheNumberOfOnesInBinary {

    public static void main(String[] args) {
//        System.out.println(-1>>1);
        int i = new TheNumberOfOnesInBinary().hammingWeight(-3);
        System.out.println(i);
    }

    public int hammingWeight(int n) {
        int sum = 0;
        if (n >= 0) {
            while (n > 0) {
                if ((n & 1) == 1) {
                    sum++;
                }
                n >>= 1;
            }
            return sum;
        } else {
            while (n != -1) {
                if ((n & 1) == 0) {
                    sum++;
                }
                n >>= 1;
            }
            return 32 - sum;
        }
    }

    public int hammingWeight1(int n) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            if ((n & (1 << i)) != 0) {
                sum++;
            }
        }
        return sum;
    }


    public int hammingWeight2(int n) {
        int sum = 0;
        while (n!=0){
            sum++;
            n &= (n-1);
        }
        return sum;
    }

}
