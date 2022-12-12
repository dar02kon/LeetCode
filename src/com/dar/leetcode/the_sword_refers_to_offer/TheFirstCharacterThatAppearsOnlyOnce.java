package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 剑指 Offer 50. 第一个只出现一次的字符 https://leetcode.cn/problems/di-yi-ge-zhi-chu-xian-yi-ci-de-zi-fu-lcof/?favorite=xb9nqhhg
 * @create :2022-12-12 08:53:00
 */
public class TheFirstCharacterThatAppearsOnlyOnce {

    public static void main(String[] args) {
        char c = new TheFirstCharacterThatAppearsOnlyOnce().firstUniqChar("leetcode");
        System.out.println(c);
    }

    public char firstUniqChar(String s) {
        int[] nums = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums[s.charAt(i)-'a']++;
        }
        for (int i = 0; i <s.length() ; i++) {
            if(nums[s.charAt(i)-'a']==1){
                return s.charAt(i);
            }
        }
        return ' ';
    }

    public char firstUniqChar2(String s) {
        Map<Character, Integer> position = new HashMap<Character, Integer>();
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            if (position.containsKey(ch)) {
                position.put(ch, -1);
            } else {
                position.put(ch, i);
            }
        }
        int first = s.length();
        for (Map.Entry<Character, Integer> entry : position.entrySet()) {
            int pos = entry.getValue();
            if (pos != -1 && pos < first) {
                first = pos;
            }
        }
        return first == s.length() ? ' ' : s.charAt(first);
    }


}
