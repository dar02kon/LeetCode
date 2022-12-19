package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * @author :wx
 * @description : 剑指 Offer 48. 最长不含重复字符的子字符串 https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-19 21:53:00
 */
public class TheLongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        int length = new TheLongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring2("pwwkew");
        System.out.println(length);
    }

    public int lengthOfLongestSubstring(String s) {
        Set<Character> set = new HashSet<>();
        int left = 0;
        int right = 0;
        int max = 0;
        while (left<=right&&right<s.length()){
            while (right<s.length()&&!set.contains(s.charAt(right))){
                set.add(s.charAt(right));
                right++;
            }
            max = Math.max(max,set.size());
            set.remove(s.charAt(left));
            left++;
        }
        return max;
    }


    public int lengthOfLongestSubstring2(String s) {
        Queue<Character> queue = new LinkedList<>();
        int max = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(queue.contains(c)){
                max = Math.max(queue.size(),max);
                while (queue.peek()!=c){
                    queue.poll();
                }
                queue.remove(c);
            }
            queue.add(c);
        }
        max = Math.max(queue.size(),max);
        return max;
    }

}
