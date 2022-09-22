package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 最后一个单词的长度 https://leetcode.cn/problems/length-of-last-word/
 * @create :2022-09-22 19:52:00
 */
public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(new LengthOfLastWord().lengthOfLastWord2("a"));
    }

    /**
     * 正则表达式分割字符串
     * @param s
     * @return
     */
    public int lengthOfLastWord(String s) {
        String[] split = s.split("\\s");//\s 是匹配所有空白符
        return split[split.length-1].length();
    }

    /**
     * 从后往前遍历计数，末尾有空格则过滤掉
     * @param s
     * @return
     */
    public int lengthOfLastWord2(String s) {
        int count = 0;
        for (int i = s.length()-1; i > -1 ; i--) {
            if(s.charAt(i)== ' '){
                if(count==0)
                    continue;
                break;
            } else {
                count++;
            }
        }
        return count;
    }

}
