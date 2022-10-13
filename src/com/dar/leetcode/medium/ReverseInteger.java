package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 整数反转 https://leetcode.cn/problems/reverse-integer/
 * @create :2022-10-13 18:37:00
 */
public class ReverseInteger {

    public static void main(String[] args) {
        System.out.println(Integer.MIN_VALUE);
        System.out.println(Integer.MAX_VALUE);
        System.out.println(new ReverseInteger().reverse(-123));
    }

    /**
     * 用Long类型来中转一下
     * @param x
     * @return
     */
    public int reverse(int x) {
        int l = 0;
        long temp;
        while (x!=0){
            temp = l* 10L +x%10;
            if(temp>=Integer.MIN_VALUE&&temp<=Integer.MAX_VALUE){
                l = (int) temp;
            } else {
                return 0;
            }
            x /= 10;
        }
        return l;
    }
    //参考：https://leetcode.cn/problems/reverse-integer/solution/zheng-shu-fan-zhuan-by-leetcode-solution-bccn/
    public int reverse2(int x) {
        int rev = 0;
        while (x != 0) {
            if (rev < Integer.MIN_VALUE / 10 || rev > Integer.MAX_VALUE / 10) {
                return 0;
            }
            int digit = x % 10;
            x /= 10;
            rev = rev * 10 + digit;
        }
        return rev;
    }


}
