package com.dar.leetcode.algorithm_training.double_pointer;

import java.util.List;

/**
 * @author :wx
 * @description : 524. 通过删除字母匹配到字典里最长单词 https://leetcode.cn/problems/longest-word-in-dictionary-through-deleting/
 * @create :2023-01-11 09:59:00
 */
public class LongestWordInDictionaryThroughDeleting {

    public static void main(String[] args) {

    }

    public String findLongestWord(String s, List<String> dictionary) {
        dictionary.sort((a, b) -> {
            if (a.length() == b.length()) return a.compareTo(b);
            return b.length() - a.length();
        });
        for (String temp : dictionary) {
            for (int i = 0,j=0; j <temp.length()&&i<s.length() ; i++) {
                if(s.charAt(i)==temp.charAt(j)) j++;
                if(j==temp.length()) return temp;
            }
        }
        return "";
    }
}
