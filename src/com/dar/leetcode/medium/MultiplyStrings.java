package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 字符串相乘 https://leetcode.cn/problems/multiply-strings/
 * @create :2022-11-02 19:33:00
 */
public class MultiplyStrings {

    public static void main(String[] args) {
        String multiply = new MultiplyStrings().multiply("123", "456");
        System.out.println(multiply);
    }

    /**
     * 列竖式算乘法
     * （字符串长度太长，很容易联想到越界导致不敢用int类型，分开来算每一步计算的数值都不大）
     * <P>
     *          例如：
                                       1    2   3
                                     * 4    5   6
                                     --------------
                                        6   12  18
                          +       5    10   15
                             4    8    12
                     ------------------------------------
                             4   13    28   27  18
                     ------------------------------------
                             4   13    28   28   8
                     ------------------------------------
                             4   13    30    8   8
                     ------------------------------------
                             4   16     0    8   8
                     ------------------------------------
                             5    6     0    8   8
     *
     * 字符串的长度最大为200（题目限制），个位数与各位数相乘最大不过81，
     * 每一列最多不过200个81相加，所以在每一列上使用int是不会越界的
     * </P>
     * @param num1
     * @param num2
     * @return
     */
    public String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) {//有0则直接返回
            return "0";
        }
        int m = num1.length(), n = num2.length();
        //使用数组来保存每一列的相加结果,一共只有m+n-1列数进行相加，多一位是考虑到进位会多出一列
        //如：99 * 99 只有 三列相加，但结果有四列
        //         9    9
        //         9    9
        //    -------------
        //        81   81
        //   81   81
        //  ----------------
        //9   8    0    1
        int[] record = new int[m + n];
        for (int i = m - 1; i >= 0; i--) {//从后往前遍历（从低位到高位）
            int num = num1.charAt(i) - '0';
            for (int j = n - 1; j >= 0; j--) {
                //计算乘法结果，加上之前的这一列的数值后再重新写入数组
                record[i + j + 1] += num * (num2.charAt(j) - '0');
            }
        }
        for (int i = m + n - 1; i > 0; i--) {//遍历数组，考虑进位，每一位只保留个位数
            record[i - 1] += record[i] / 10;//进位
            record[i] %= 10;//取余，保留个位
        }
        int index = record[0] == 0 ? 1 : 0;//判断是否存在最终进位（即多出一列）
        StringBuilder result = new StringBuilder();
        while (index < m + n) {//遍历数组，拼接字符串
            result.append(record[index]);
            index++;
        }
        return result.toString();
    }
}
