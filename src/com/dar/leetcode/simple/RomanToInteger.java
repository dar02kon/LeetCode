package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :wx
 * @description :
 * @create :2022-09-19 20:22:00
 */
public class RomanToInteger {
    public static void main(String[] args) {
        System.out.println(new RomanToInteger().romanToInt2("MCMXCIV"));
    }

    /**
     * 简单粗暴：
     * 遍历字符串如果出现左边字符比右边大，则减去这个字符代表的数字，负责加上这个字符代表的数字
     * @param s
     * @return
     */
    public int romanToInt1(String s) {
        Map<Character,Integer> map = new HashMap<>(7);
        map.put('I',1);
        map.put('V',5);
        map.put('X',10);
        map.put('L',50);
        map.put('C',100);
        map.put('D',500);
        map.put('M',1000);
        int num = 0;
        for (int i = s.length()-1; i > -1 ; i--) {
            if(i<s.length()-1 && map.get(s.charAt(i))<map.get(s.charAt(i+1))){//出现左边比右边大
                num -= map.get(s.charAt(i));
            } else {
                num += map.get(s.charAt(i));
            }
        }
        return num;
    }

    /**
     * 思路与上面一样，只不过没有使用Map存储数据
     * @param s
     * @return
     */
    public int romanToInt2(String s) {
        int num = 0;
        for (int i = s.length()-1; i > -1 ; i--) {
            if(i<s.length()-1 && getNum(s.charAt(i))<getNum(s.charAt(i+1))){//出现左边比右边大
                num -= getNum(s.charAt(i));
            } else {
                num += getNum(s.charAt(i));
            }
        }
        return num;

    }
    public int getNum(Character c){
        switch (c){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return 0;
        }
    }
}
