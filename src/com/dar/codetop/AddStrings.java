package com.dar.codetop;

/**
 * @author :wx
 * @description : 415. 字符串相加 https://leetcode.cn/problems/add-strings/description/
 * @create :2023-07-30 19:39:00
 */
public class AddStrings {
    /**
     * 模拟加法即可
     */
    public String addStrings(String num1, String num2) {
        // 存储结果
        StringBuilder result = new StringBuilder();
        // 进位
        int temp = 0;
        // 从后往前遍历
        int index1 = num1.length() - 1;
        int index2 = num2.length() - 1;
        while (index1 >= 0 || index2 >= 0) {
            int num = temp;
            if (index1 >= 0) {
                num += num1.charAt(index1) - '0';
                index1--;
            }
            if (index2 >= 0) {
                num += num2.charAt(index2) - '0';
                index2--;
            }
            // 进位
            temp = num / 10;
            // 当前位
            num = num % 10;
            result.insert(0, num);
        }
        // 最后可能存在进位，不能忘了
        if (temp != 0) {
            result.insert(0, temp);
        }
        return result.toString();
    }
}
