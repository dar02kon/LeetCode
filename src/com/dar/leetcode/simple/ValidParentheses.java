package com.dar.leetcode.simple;

import java.util.Stack;

/**
 * @author :wx
 * @description : 有效的括号  https://leetcode.cn/problems/valid-parentheses/
 * @create :2022-09-20 19:22:00
 */
public class ValidParentheses {
    public static void main(String[] args) {
        System.out.println(new ValidParentheses().isValid2("([)]"));
    }

    /**
     * 如果字符串为空直接返回true
     * 如果字符串的位数为奇数则直接返回false
     *
     * 引入栈，遍历字符数组
     * 如果是左符号则把对应的右符号进栈
     * 如果是右符号则与栈顶比较，栈空或者不相等则直接返回false
     * 遍历结束栈空（有效）返回true，否则返回false
     * @param s
     * @return
     */
    public boolean isValid2(String s) {
        if(s.isEmpty()){
            return true;
        }
        if(s.length()%2!=0)
            return false;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            //栈中元素个数如果超过字符串中字符的个数则括号一定不会完全匹配
            if(stack.size()>s.length()/2){
                return false;
            }
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        return stack.empty();
    }

    /**
     * 通过测试用例：87 / 92
     * 原因：左括号必须以正确的顺序闭合。
     * @param s
     * @return
     */
    public boolean isValid1(String s) {
        while (s.length()>0){
            s = getString(s);
            if (s==null){
                return false;
            }
        }
        return true;

    }
    public String getString(String s){
        char c = s.charAt(0);
        switch (c){
            case '(':
                if(s.indexOf(')') > -1){
                    return s.replaceFirst("\\(","").replaceFirst("\\)","");
                } else {
                    return null;
                }
            case '[':
                if(s.indexOf(']') > -1){
                    return s.replaceFirst("\\[","").replaceFirst("]","");
                } else {
                    return null;
                }
            case '{':
                if(s.indexOf('}') > -1){
                    return s.replaceFirst("\\{","").replaceFirst("}","");
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

}
