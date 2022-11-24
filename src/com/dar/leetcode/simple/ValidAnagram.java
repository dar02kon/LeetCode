package com.dar.leetcode.simple;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 有效的字母异位词 https://leetcode.cn/problems/valid-anagram/description/
 * @create :2022-11-24 16:12:00
 */
public class ValidAnagram {

    public static void main(String[] args) {
        boolean b = new ValidAnagram().isAnagram("anagram", "nagaram");
        System.out.println(b);
    }

    /**
     * 使用一个数组来记录字符出现的次数
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()){//大小不相同
            return false;
        }
        int[] count = new int[26];//记录字符出现的次数
        for (int i = 0; i < s.length(); i++) {//先加
            count[s.charAt(i)-'a']++;
        }
        for (int i = 0; i < t.length(); i++) {//后减
            count[t.charAt(i)-'a']--;
            if(count[t.charAt(i)-'a']<0){//出现次数小于0
                return false;
            }
        }
        return true;
    }

    public boolean isAnagram1(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i)-'a']++;//先加
            count[t.charAt(i)-'a']--;//后减
        }
        for (int i : count) {// 如果 s 和 t 互为字母异位词，则count数组中的值应为0
            if(i!=0){
                return false;
            }
        }
        return true;
    }

    /**
     * 排序后比较字符串是否相同
     * 就时间复杂度和空间复杂度来说意义不大
     * @param s
     * @param t
     * @return
     */
    public boolean isAnagram2(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        char[] s1 = s.toCharArray();//转数组
        char[] t1 = t.toCharArray();//转数组
        Arrays.sort(s1);//排序
        Arrays.sort(t1);//排序
        return Arrays.equals(s1,t1);//比较
    }
}
