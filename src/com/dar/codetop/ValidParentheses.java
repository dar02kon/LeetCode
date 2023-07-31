package com.dar.codetop;

import java.util.*;

/**
 * @author :wx
 * @description : 20. 有效的括号 https://leetcode.cn/problems/valid-parentheses/
 * @create :2023-07-31 21:59:00
 */
public class ValidParentheses {
    /**
     * 利用栈进行匹配
     */
    public boolean isValid(String s) {
        Map<Character, Character> map = new HashMap<>();
        // 匹配关系
        map.put(')', '(');
        map.put(']', '[');
        map.put('}', '{');
        if (s == null || s.length() == 0) return true;
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            // 左括号进栈
            if (map.containsValue(s.charAt(i))) {
                stack.push(s.charAt(i));
            }
            // 右括号寻求匹配
            if (map.containsKey(s.charAt(i))) {
                if (stack.isEmpty() || stack.pop() != map.get(s.charAt(i))) {
                    return false;
                }
            }
        }
        // 最后匹配完栈空
        return stack.isEmpty();
    }
}
