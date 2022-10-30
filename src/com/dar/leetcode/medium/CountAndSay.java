package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 外观数列 https://leetcode.cn/problems/count-and-say/
 * @create :2022-10-30 15:59:00
 */
public class CountAndSay {

    public static void main(String[] args) {
        String s = new CountAndSay().countAndSay(5);
        System.out.println(s);
    }

    /**
     * 循环求解（数据量小，也可以打表...）
     * 根据当前字符串去获取下一个字符串
     * <p>
     *     从左往右进行遍历，记录当前字符出现的次数
     *     次数+字符 即为下一个字符串的组成部分
     * </p>
     * @param n
     * @return
     */
    public String countAndSay(int n) {
        String s = "1";
        if(n==1){//特殊处理
            return s;
        }
        for (int i = 1; i < n ; i++) {
            s = getNextString(s);
        }
        return s;

    }

    /**
     * 获取下一个字符串
     * @param s
     * @return
     */
    public String getNextString(String s){
        StringBuilder result = new StringBuilder();
        char c = s.charAt(0);//记录当前字符
        int count = 0;//记录字符出现次数
        for (int i = 0; i <s.length() ; i++) {
            if(s.charAt(i)==c){//若重复出现，次数加1
                count++;
            } else {//更换字符
                result.append(count).append(c);//将之前的 次数+字符 信息添加到字符串中
                c = s.charAt(i);//更换字符
                count = 1;//初始化次数
            }
        }
        result.append(count).append(c);//注意添加最后的信息
        return result.toString();
    }
}
