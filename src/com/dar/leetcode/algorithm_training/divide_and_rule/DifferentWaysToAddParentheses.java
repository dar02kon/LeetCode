package com.dar.leetcode.algorithm_training.divide_and_rule;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 241. 为运算表达式设计优先级 https://leetcode.cn/problems/different-ways-to-add-parentheses/
 * @create :2023-01-23 14:17:00
 */
public class DifferentWaysToAddParentheses {

    public static void main(String[] args) {
        List<Integer> list = new DifferentWaysToAddParentheses().diffWaysToCompute("2*3-4*5");
        System.out.println(list);
    }

    public List<Integer> diffWaysToCompute(String expression) {
        int n = expression.length();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {//依次分解
            char c = expression.charAt(i);
            if (c == '+' || c == '-' || c == '*') {//以运算符为界限分解
                List<Integer> lefts = diffWaysToCompute(expression.substring(0, i));//继续递归分解运算符左边部分
                List<Integer> rights = diffWaysToCompute(expression.substring(i + 1));//继续递归分解运算符右边部分
                //将左边与右边的结果根据运算符进行合并
                for (Integer left : lefts) {
                    for (Integer right : rights) {
                        switch (c) {
                            case '+':
                                result.add(left + right);
                                break;
                            case '-':
                                result.add(left - right);
                                break;
                            default:
                                result.add(left * right);
                        }
                    }
                }

            }
        }
        //只有一个数字，添加到集合中（参加后面的运算）
        if (result.size() == 0) result.add(Integer.valueOf(expression));
        return result;
    }

}
