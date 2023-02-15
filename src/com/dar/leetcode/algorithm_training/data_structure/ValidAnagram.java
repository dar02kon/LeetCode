package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 242. 有效的字母异位词 https://leetcode.cn/problems/valid-anagram/
 * @create :2023-02-15 16:04:00
 */
public class ValidAnagram {

    public static void main(String[] args) {

    }

    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }
        for (int num : count) {
            if (num != 0) {
                return false;
            }
        }
        return true;
    }
}
