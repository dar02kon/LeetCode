package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 9. 回文数 https://leetcode.cn/problems/palindrome-number/
 * @create :2023-02-16 15:37:00
 */
public class PalindromeNumber {

    public static void main(String[] args) {

    }

    public boolean isPalindrome(int x) {
        if(x==0) return true;
        if(x<0||x%10==0) return false;
        int reverse = 0;
        int temp = x;
        while (temp!=0){
            reverse = reverse*10+temp%10;
            temp/=10;
        }
        return x==reverse;
    }

    public boolean isPalindrome2(int x) {
        if(x==0) return true;
        if(x<0||x%10==0) return false;

        int reverse = 0;
        while (x>reverse){
            reverse = reverse*10+x%10;
            x/=10;
        }
        return x==reverse||x==reverse/10;
    }
}
