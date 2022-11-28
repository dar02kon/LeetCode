package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 剑指 Offer 30. 包含min函数的栈 https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/
 * @create :2022-11-28 13:30:00
 */
public class TheStackContainingTheMinFunction {

    public static void main(String[] args) {

    }

}
class MinStack {
    private final List<Integer> list;
    private final List<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayList<>();
        min = new ArrayList<>();
    }

    public void push(int x) {
        list.add(x);
        if(min.size()==0){
            min.add(x);
        } else {
            min.add(Math.min(min.get(min.size()-1),x));
        }
    }

    public void pop() {
        if(list.size()>0){
            Integer remove = list.remove(list.size() - 1);
            min.remove(min.size()-1);
        }
    }

    public int top() {
        if(list.size()>0){
            return list.get(list.size()-1);
        } else {
            return -1;
        }
    }

    public int min() {
        return min.get(min.size()-1);
    }
}