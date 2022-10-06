package com.dar.leetcode.simple;

import java.util.HashSet;
import java.util.Set;

/**
 * @author :wx
 * @description : 快乐数 https://leetcode.cn/problems/happy-number/
 * @create :2022-10-06 18:38:00
 */
public class HappyNumber {

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy3(2));
    }

    /**
     * 使用hash表存储已经出现过的数字，
     * 如果再次出现则直接返回false（开始循环了）
     * 将数字转成字符串循环求其各位上的平方和
     * 结果为1返回true
     *
     * 如果不是快乐数 为什么它一定会循环而不是一直增加呢
     *      位数  最大值     各位上的平方和
     *      1       9           81
     *      2       99          162
     *      3       999         243
     *      4       9999        324
     *      5       99999       405
     * 可以发现4位之后各位上的平方和都会减少（至少一位），直到降到3位为止
     * 所以最坏在243以内循环
     * 仔细一想n各位上的平方和最大不过是9的倍数，在n的位数比较大时，它就显得很渺小
     * @param n
     * @return
     */
    public boolean isHappy(int n) {
        Set<String> set = new HashSet<>();
        String s = n+"";
        while (!s.equals("1")){
            set.add(s);
            s = getPow(s)+"";
            if(set.contains(s)){
                return false;
            }
        }
        return true;
    }
    public int getPow(String n){
        int result = 0;
        for (int i = 0; i < n.length(); i++) {
            result += Math.pow(n.charAt(i)-'0',2);
        }
        return result;
    }

    /**
     * 优化一下：
     * 直接使用数字进行运算
     * @param n
     * @return
     */
    public boolean isHappy2(int n) {
        Set<Integer> set = new HashSet<>();
        while (n!=1){
            int result = 0;
            set.add(n);
            while (n>0){
                result += (n%10)*(n%10);
                n /= 10;
            }
            n = result;
            if(set.contains(n)){
                return false;
            }
        }
        return true;
    }

    /**
     * 使用hash表存储出现过的数字就像是在判断链表是否有环时存储节点一样
     * 所以可以用龟兔赛跑算法来进一步优化
     * @param n
     * @return
     */
    public boolean isHappy3(int n) {
        int low = n;
        int fast = getPow2(n);
        if(low == 1 || fast ==1){
            return true;
        }
        while (low!=1){
            if(low==fast){//有环
                return false;
            }
            low = getPow2(low);//移动一步
            fast = getPow2(getPow2(fast));//移动两步
        }
        return true;

    }
    public int getPow2(int num){
        int result = 0;
        while (num>0){
            result += (num%10)*(num%10);
            num /=10 ;
        }
        return result;
    }

    /**
     * 还有一种思路，就是找出造成循环的那几个值，在243以内通过程序计算是可以实现的
     */


}