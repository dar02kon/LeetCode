package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 696. 计数二进制子串 https://leetcode.cn/problems/count-binary-substrings/description/
 * @create :2023-02-17 15:54:00
 */
public class CountBinarySubstrings {

    public static void main(String[] args) {

    }

    public int countBinarySubstrings(String s) {
        int index = 0;
        int result = 0;
        int temp = 0;
        int count;
        while (index<s.length()){
            count = 0;
            char c = s.charAt(index);
            while (index<s.length()&&s.charAt(index)==c){
                count++;
                index++;
            }
            result+=Math.min(count,temp);
            temp = count;
        }
        return result;
    }
}
