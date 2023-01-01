package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 65. 不用加减乘除做加法 https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-31 20:48:00
 */
public class AddWithoutAdditionSubtractionMultiplicationAndDivision {

    public static void main(String[] args) {
        int add = new AddWithoutAdditionSubtractionMultiplicationAndDivision().add(1, 2);
        System.out.println(add);
    }

    public int add(int a, int b) {
        while (b!=0){
            int temp = b;
            b = (a&b)<<1;//进位
            a = a^temp;//本位
        }
        return a;
    }
}
