package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 131. 分割回文串 https://leetcode.cn/problems/palindrome-partitioning/
 * @create :2023-01-21 15:22:00
 */
public class PalindromePartitioning {

    public static void main(String[] args) {

    }

    List<List<String>> list = new ArrayList<>();
    List<String> record = new ArrayList<>();

    public List<List<String>> partition(String s) {
        combination(s);
        return list;
    }

    public void combination(String s) {
        if (s.length() == 0) {
            list.add(new ArrayList<>(record));
            return;
        }
        for (int i = 0; i < s.length(); i++) {
            if (check(s, i)) {//截取的字符串为回文串则继续递归添加
                record.add(s.substring(0, i + 1));
                combination(s.substring(i + 1));
                record.remove(record.size() - 1);
            }
        }
    }

    // 判断是否为回文串
    private boolean check(String s, int end) {
        int start = 0;
        while (start < end) {
            if (s.charAt(start++) != s.charAt(end--)) return false;
        }
        return true;
    }
}
