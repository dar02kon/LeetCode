package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 颠倒二进制位 https://leetcode.cn/problems/reverse-bits/
 * @create :2022-10-05 19:12:00
 */
public class ReverseBits {

    public static void main(String[] args) {
        System.out.println(new ReverseBits().reverseBits2(-3));
    }

    /**
     * 将数字转二进制，不足32位需要补0
     * 从后往前遍历转十进制
     * @param n
     * @return
     */
    public int reverseBits(int n) {
        StringBuilder s = new StringBuilder(Integer.toBinaryString(n));
        int result = 0;
        for (int i = s.length(); i < 32 ; i++) {
            s.insert(0, "0");
        }
        for (int i = s.length()-1; i >= 0 ; i--) {
            result = result*2 + (s.charAt(i)-'0');
        }
        return result;
    }

    /**
     * 本地测试肯定是错的，但如果你把n当成32位二进制数那就是对的
     * 所以就是做个反转就行
     * 每次将sum左移一位（sum<<1），接受n的最后一位，然后将n右移一位（n>>1）
     * 如果你将n转字符串后直接反转那肯定是不行的，因为你反转的是十进制，不是二进制
     * <p>
     *     左移运算符（<<）运算规则：按二进制形式把所有的数字向左移动对应的位数，高位移出(舍弃)，低位的空位补零。
     *     右移运算符（>>）运算规则：按二进制形式把所有的数字向右移动对应的位数，低位移出(舍弃)，高位的空位补符号位，即正数补零，负数补1。
     * </p>
     * @param n
     * @return
     */
    public int reverseBits2(int n) {
        int sum=0;
        for(int i=0;i<32;i++){
            sum=(sum<<1)+((n>>i)&1);
        }
        return sum;
    }
}
