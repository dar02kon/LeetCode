package com.dar.leetcode.algorithm_training.greedy_thinking;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 455. 分发饼干 https://leetcode.cn/problems/assign-cookies/description/
 * @create :2023-01-08 09:17:00
 */
public class AssignCookies {

    public static void main(String[] args) {

    }

    public int findContentChildren(int[] g, int[] s) {
        //排序
        Arrays.sort(g);
        Arrays.sort(s);
        int i=0,j=0;
        while (i<g.length&&j<s.length){
            if(s[j]>=g[i]) i++;//满足，下一个小孩
            j++;//换饼干（饼干可能被吃了也有可能太小了选择跳过）
        }
        return i;
    }
}
