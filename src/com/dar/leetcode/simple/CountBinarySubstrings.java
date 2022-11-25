package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 计数二进制子串 https://leetcode.cn/problems/count-binary-substrings/
 * @create :2022-11-25 15:37:00
 */
public class CountBinarySubstrings {
    public static void main(String[] args) {
        int i = new CountBinarySubstrings().countBinarySubstrings2("00110011");
        System.out.println(i);
    }

    /**
     * 我们可以提前根据0和1将字符串分组，统计每一组中元素的个数，
     * 同一组的元素要么同为0要么同为1，
     * 相邻组的元素不可能出现相同
     * 相邻的两组都可以组成若干个符合要求的字符串，个数为两组元素个数的最小值。
     * 遍历所有相邻的数对，求它们的贡献总和，即可得到结果
     *
     * "00110011"可以分为4组，`[2,2,2,2]`，一二两组可以提供 2 个符合要求的字符串，
     * 二三与三四同样也可以提供 2 个符合要求的字符串，所以最终结果为 6
     * @param s
     * @return
     */
    public int countBinarySubstrings(String s) {
        List<Integer> list = new ArrayList<>();
        int index = 0;
        int count;
        while (index < s.length()) {//求分组
            char c = s.charAt(index);
            count = 0;
            while (index < s.length() && s.charAt(index) == c) {
                count++;
                index++;
            }
            list.add(count);
        }
        count = 0;
        for (int i = 1; i < list.size(); i++) {//遍历所有相邻的数对，求它们的贡献总和
            count += Math.min(list.get(i - 1), list.get(i));
        }
        return count;
    }

    /**
     * 其实我们最终只需要求相邻数对的贡献，所以只需要用一个变量记录前一组的元素个数
     * @param s
     * @return
     */
    public int countBinarySubstrings2(String s) {
        int index = 0;//下标索引
        int num = 0;//记录前一组元素个数
        int result = 0;//记录结果
        int count;//计数
        while (index < s.length()) {
            char c = s.charAt(index);
            count = 0;
            while (index < s.length() && s.charAt(index) == c) {//组中的元素个数
                count++;
                index++;
            }
            result += Math.min(num, count);//记录相邻数对的贡献
            num = count;
        }
        return result;
    }
}
