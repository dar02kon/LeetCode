package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 面试题45. 把数组排成最小的数 https://leetcode.cn/problems/ba-shu-zu-pai-cheng-zui-xiao-de-shu-lcof/?favorite=xb9nqhhg
 * @create :2023-01-04 10:52:00
 */
public class ArrangeTheArrayAsTheSmallestNumberAsPossible {

    public static void main(String[] args) {

    }

    public String minNumber(int[] nums) {
        String[] temp = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            temp[i] = nums[i]+"";
        }
        Arrays.sort(temp,(x,y)->(x+y).compareTo(y+x));
        StringBuilder result = new StringBuilder();
        for (String s : temp) {
            result.append(s);
        }
        return result.toString();
    }
}
