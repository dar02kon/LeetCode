package com.dar.leetcode.algorithm_training.data_structure;

import java.util.Stack;

/**
 * @author :wx
 * @description : 20. 有效的括号 https://leetcode.cn/problems/valid-parentheses/description/
 * @create :2023-02-12 10:33:00
 */
public class ValidParentheses {

    public static void main(String[] args) {

    }

    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i <s.length() ; i++) {
            char c = s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            } else {
                if(stack.isEmpty()){
                    return false;
                }
                Character pop = stack.pop();
                if((c==')'&&pop!='(')||(c==']'&&pop!='[')||(c=='}'&&pop!='{')){
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
