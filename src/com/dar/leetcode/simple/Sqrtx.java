package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : x 的平方根  https://leetcode.cn/problems/sqrtx/
 * @create :2022-09-23 19:32:00
 */
public class Sqrtx {
    public static void main(String[] args) {
        System.out.println(new Sqrtx().mySqrt(2147483647));
    }

    /**
     * 简单粗暴：
     * 先判断x的位数
     * 算术根的位数范围在(len+1)/2与(len+1)/2+1之间
     * i*i结果需要转成long型（可能会溢出）
     * @param x
     * @return
     */
    public int mySqrt(int x) {
        int len = (x+"").length()+1;
        int start = getNum(len/2);
        int end = getNum(len/2+1);
        for (int i = start; i < end ; i++) {
            if((long)i*i>=x){
                return x == i*i ? i : i-1;
            }
        }
        return 1;
    }
    public int getNum(int i){
        if(i==0){
            return 0;
        }
        int count = 1;
        for (int j = 1; j < i ; j++) {
            count*=10;
        }
        return count;
    }

    /**
     * 用了其它公式（将算术根写成幂的形式，用自然对数e进行换底）
     * @param x
     * @return
     */
    public int mySqrt2(int x) {
        if (x == 0) {
            return 0;
        }
        int ans = (int) Math.exp(0.5 * Math.log(x));
        return (long) (ans + 1) * (ans + 1) <= x ? ans + 1 : ans;
    }

    /**
     * 二分查找
     * 最终的mid一定是最靠近算术根的（选小的那一个）
     * @param x
     * @return
     */
    public int mySqrt3(int x) {
        int start = 0;
        int end = x;
        int result = 0;
        while (start<=end){
            int mid = start + (end - start)/2;
            if((long)mid*mid<=x){
                result = mid;
                start = mid +1;
            } else {
                end = mid - 1;
            }
        }
        return result;
    }



}
