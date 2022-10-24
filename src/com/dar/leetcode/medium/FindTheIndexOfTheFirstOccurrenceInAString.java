package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 找出字符串中第一个匹配项的下标 https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/
 * @create :2022-10-24 15:47:00
 */
public class FindTheIndexOfTheFirstOccurrenceInAString {

    public static void main(String[] args) {
        int i = new FindTheIndexOfTheFirstOccurrenceInAString().strStr2("aaa", "aaaa");
        System.out.println(i);
    }

    /**
     * 遍历截取定长的字符串与目标字符串进行比较
     * 相同返回开始下标值
     * 循环结束返回-1
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr(String haystack, String needle) {
        for (int i = 0; i < haystack.length()-needle.length()+1 ; i++) {
            String s = haystack.substring(i,i+needle.length());
            if(s.equals(needle)){
                return i;
            }
        }
        return -1;
    }

    /**
     * 在上面的基础上加了一些条件，条件满足时才截取字符串去进行比较
     * @param haystack
     * @param needle
     * @return
     */
    public int strStr1(String haystack, String needle) {
        for (int i = 0; i < haystack.length()-needle.length()+1 ; i++) {
            if(haystack.charAt(i)==needle.charAt(0)&&haystack.charAt(i+needle.length()-1)==needle.charAt(needle.length()-1)){
                String s = haystack.substring(i,i+needle.length());
                if(s.equals(needle)){
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * KMP算法（原理和思路放在README.md，代码里实在说不明白）
     * @param haystack 主串
     * @param needle 模式串
     * @return 字符串中第一个匹配项的下标
     */
    public int strStr2(String haystack, String needle) {
        //模式串的自匹配，构建next数组
        int[] next = new int[needle.length()];//记录前缀与后缀的最长公共子串长度
        for (int i = 1,j = 0; i < needle.length() ; i++) {//用模式串来构建
            while (j>0&&needle.charAt(i)!=needle.charAt(j)){//不相同且j大于0，j指针回溯
                j = next[j-1];
            }
            if(needle.charAt(i)==needle.charAt(j)){//相同，i指针，j指针同时后移（i指针的移动在for循环里）
                j++;
            }
            next[i] = j;//存储前缀与后缀的最长公共子串长度（这个时候j已经在原有的基础上加1了，除非j=0）
        }
        //字符串匹配
        for (int i = 0,j=0; i < haystack.length(); i++) {
            while (j>0&&haystack.charAt(i)!=needle.charAt(j)){//j=0时，只考虑移动i指针直到haystack.charAt(i)==needle.charAt(j)
                j = next[j-1];//j指针回溯
            }
            if(haystack.charAt(i)==needle.charAt(j)){//单个字符匹配成功，i指针与j指针前移
                j++;
            }
            if(j==needle.length()){//返回第一个匹配项的下标
                return i-j+1;
            }
        }
        return -1;//没有匹配项返回-1
    }
}
