package com.dar.leetcode.algorithm_training.bit_operation;

/**
 * @author :wx
 * @description : 190. 颠倒二进制位 https://leetcode.cn/problems/reverse-bits/
 * @create :2023-03-25 13:44:00
 */
public class ReverseBits {

    public static void main(String[] args) {

    }
    // 倒序求值
    public int reverseBits(int n) {
        int[] nums = new int[32];
        int index = 0;
        while (n!=0&&index<32){
            if((n&1)==1){
                nums[index] = 1;
            }
            index++;
            n>>=1;
        }
        int result = 0;
        for (int num : nums) {
            result = result*2+num;
        }
        return result;
    }

    public int reverseBits2(int n) {
        int result = 0;
        for (int i = 0; i <32 ; i++) {
            result<<=1;
            result |= (n&1);
            n>>>=1;
        }
        return result;
    }

}
