package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 91. 解码方法 https://leetcode.cn/problems/decode-ways/
 * @create :2023-01-25 14:21:00
 */
public class DecodeWays {

    public static void main(String[] args) {

    }

    public int numDecodings(String s) {
        if (s.charAt(0) == '0') return 0;
        int pre1 = 1;//前一位
        int pre2 = 1;//前两位
        int dp;
        for (int i = 1; i < s.length(); i++) {
            int num = (s.charAt(i - 1) - '0') * 10 + (s.charAt(i) - '0');//组合数字
            if (s.charAt(i) != '0') {//该位不为0
                if (s.charAt(i - 1) != '0' && num >= 1 && num <= 26) {//组合数字可以映射 形如 122
                    dp = pre1 + pre2;
                } else {//不能映射 形如 206
                    dp = pre2;
                }
            } else {//该位为0
                if (s.charAt(i - 1) != '0' && num >= 1 && num <= 26) {//组合数字可以映射 形如 20
                    dp = pre1;
                } else {//不能映射 形如 60
                    return 0;
                }
            }
            pre1 = pre2;
            pre2 = dp;
        }
        return pre2;
    }
}
