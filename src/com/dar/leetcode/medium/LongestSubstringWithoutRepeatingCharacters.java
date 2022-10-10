package com.dar.leetcode.medium;

import java.util.*;

/**
 * @author :wx
 * @description : 无重复字符的最长子串 https://leetcode.cn/problems/longest-substring-without-repeating-characters/
 * @create :2022-10-10 17:15:00
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
        System.out.println(new LongestSubstringWithoutRepeatingCharacters().lengthOfLongestSubstring("pwwkew"));
    }

    /**
     * 滑动窗口
     * 使用一个队列来存储窗口元素
     * 若窗口中已经存在目标元素，则将该元素之前的所有元素都删除
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
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

    /**
     * 上面使用队列来存储元素时，我们删除元素需要循环遍历
     *  可以改为用HashMap来存储元素与元素的下标值
     *  同时除了需要有一个变量记录最大值还需要一个左指针（这样我们就没有必要删除元素了，只需要移动指针即可）
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring2(String s) {
        if (s.length()==0) return 0;
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        int max = 0;
        int left = 0;
        for(int i = 0; i < s.length(); i ++){
            if(map.containsKey(s.charAt(i))){
                left = Math.max(left,map.get(s.charAt(i)) + 1);//+1 是因为我们需要的是已存在元素的下一位
            }
            map.put(s.charAt(i),i);
            max = Math.max(max,i-left+1);//+1是因为要包含自己
        }
        return max;

    }

}
