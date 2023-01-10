package com.dar.leetcode.algorithm_training.double_pointer;

/**
 * @author :wx
 * @description : 345. 反转字符串中的元音字母 https://leetcode.cn/problems/reverse-vowels-of-a-string/
 * @create :2023-01-10 09:32:00
 */
public class ReverseVowelsOfAString {

    public static void main(String[] args) {

    }

    public String reverseVowels(String s) {
        char[] array = s.toCharArray();
        int left = 0,right = s.length()-1;
        while (left<right){
            if(check(array[left])&&check(array[right])){
                char temp = array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            } else if(check(array[left])){
                right--;
            } else {
                left++;
            }
        }
        return new String(array);
    }

    public boolean check(char c){
        return c=='a'||c=='e'||c=='i'||c=='o'||c=='u'|| c=='A'||c=='E'||c=='I'||c=='O'||c=='U';
    }
}
