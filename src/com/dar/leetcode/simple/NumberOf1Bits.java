package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 位1的个数 https://leetcode.cn/problems/number-of-1-bits/
 * @create :2022-10-05 20:52:00
 */
public class NumberOf1Bits {

    public static void main(String[] args) {
    }

    /**
     * 位运算，比较每一位是否为1
     * n&1 就是32位二进制数n的最后一位
     * 每一次比较后 n都需要右移一位，
     * 不想改变n的值，需要在比较前将n右移i位
     * @param n
     * @return
     */
    public int hammingWeight(int n) {
        int count = 0;
        for (int i = 0; i < 32; i++) {
            if(((n>>i) & 1)==1){
                count++;
            }
        }
        return count;
    }

    /**
     * 还有一种思路：
     * 通过n&(n-1)的方式将n清0
     * 循环的次数即为1的个数
     *
     * <p>
     *     说明：
     *      n是32位二进制数，n>0时 n-1 一定会把n中的某个位上的1变为0，但也有可能将0变为1（如10-1=01）
     *      所以就需要 n = n&(n-1)来保证每次操作必定会将n中的1减少一个
     *      n&(n-1)为什么会使n少一个1呢？
     *          因为1&1=0，1&0=0，0&0=0，n&(n-1)会使n中为0的位数保持不变
     *          并在此基础上，将n-1中那个由1变为0对应的那一位1也变成0，
     *          所以总体上来说n = n&(n-1)与原来的n差别就是少一个1，多一个0
     * </p>
     *
     * @param n
     * @return
     */
    public int hammingWeight2(int n) {
        int count = 0;
        while (n!=0){
            count++;
            n = n&(n-1);
        }
        return count;
    }


}
