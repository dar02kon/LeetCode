package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.LinkedList;
import java.util.List;

/**
 * @author :wx
 * @description : 剑指 Offer 05. 替换空格 https://leetcode.cn/problems/ti-huan-kong-ge-lcof/?favorite=xb9nqhhg
 * @create :2022-11-09 15:49:00
 */
public class ReplaceSpaces {

    public static void main(String[] args) {
        String s = new ReplaceSpaces().replaceSpace2("We are happy.");
        System.out.println(s);
    }

    /**
     * 使用数组来存储结果
     * Java中的字符串是不可变得（底层使用的是用final修饰的char/byte数组）
     * 最后将字符数组转字符串
     * @param s
     * @return
     */
    public String replaceSpace(String s) {
        char[] result = new char[s.length()*3];//初始化
        int index = 0;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(c==' '){
                result[index++] = '%';
                result[index++] = '2';
                result[index++] = '0';
            } else {
                result[index++] = c;
            }
        }
        return new String(result,0,index);
    }

    /**
     * 使用StringBuilder来保存数据
     * StringBuilder可以”动态“申请空间（其实也是重新初始化数组）
     * 不过我们可以尝试去先去申请一个合适大小的空间来减少重新初始化数组的次数
     * @param s
     * @return
     */
    public String replaceSpace2(String s) {
        StringBuilder result = new StringBuilder(s.length());//初始化，最初的大小可以根据数据来进行估算以减少重新申请空间的次数
        for (int i = 0; i < s.length() ; i++) {
            if(s.charAt(i)==' '){
                result.append("%20");
            } else {
                result.append(s.charAt(i));
            }
        }
        return result.toString();
    }

}
