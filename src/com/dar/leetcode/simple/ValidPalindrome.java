package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : 验证回文串 https://leetcode.cn/problems/valid-palindrome/
 * @create :2022-09-27 21:19:00
 */
public class ValidPalindrome {
    public static void main(String[] args) {
        System.out.println(new ValidPalindrome().isPalindrome("A man, a plan, a canal: Panama"));
    }

    /**
     * 双指针，从两边往中间靠拢
     * @param s
     * @return
     */
    public boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length()-1;
        s = s.toLowerCase();
        while (left<=right){
            if(isNumOrLetter(s.charAt(left))){
                if(isNumOrLetter(s.charAt(right))){
                    if(!(s.charAt(left)==s.charAt(right))){
                        return false;
                    } else {
                        left++;
                        right--;
                    }
                } else {
                    right--;
                }
            } else {
                left++;
            }
        }
        return true;
    }

    public boolean isNumOrLetter(char c){
        return (c>='0'&&c<='9')||(c>='a'&&c<='z');
    }
}
