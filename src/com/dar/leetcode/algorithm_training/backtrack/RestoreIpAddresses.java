package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 93. 复原 IP 地址 https://leetcode.cn/problems/restore-ip-addresses/description/
 * @create :2023-01-18 08:55:00
 */
public class RestoreIpAddresses {

    public static void main(String[] args) {

    }

    List<String> list;
    StringBuilder result = new StringBuilder();

    public List<String> restoreIpAddresses(String s) {
        list = new ArrayList<>();
        combination(0, 0, s);
        return list;
    }

    public void combination(int count, int start, String s) {
        if (count == 4 || start == s.length()) {//判断结果是否符合要求
            if (count == 4 && start == s.length()) {
                list.add(result.toString());
            }
            return;
        }

        for (int i = start; i <= start + 2 && i < s.length(); i++) {
            if (i != start && s.charAt(start) == '0') break;//存在前导0（允许一个0单独存在）
            String sub = s.substring(start, i + 1);
            if (Integer.parseInt(sub) <= 255) {//判断数字大小
                //追加字符串
                if (start != 0) {
                    result.append('.').append(sub);
                } else {
                    result.append(sub);
                }
                combination(count + 1, i + 1, s);//递归添加
                //删除追加的字符串
                if (start != 0) {
                    result.delete(result.length() - sub.length() - 1, result.length());
                } else {
                    result.delete(0, result.length());
                }
            }
        }
    }
}
