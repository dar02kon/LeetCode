package com.dar.leetcode.algorithm_training.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 17. 电话号码的字母组合 https://leetcode.cn/problems/letter-combinations-of-a-phone-number/description/
 * @create :2023-01-17 19:14:00
 */
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {

    }

    String[] KEYS = {"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    List<String> list = new ArrayList<>();
    StringBuilder result = new StringBuilder();//用于字母拼接，保存结果
    public List<String> letterCombinations(String digits) {
        if(digits!=null&&digits.length()>0){
            combination(digits,0);//从第一位数字开始
        }
        return list;
    }

    public void combination(String digits,int index){
        if(digits.length()==index){//字符串拼接完成
            list.add(result.toString());
            return;
        }
        String key = KEYS[digits.charAt(index) - '0'];
        for (int i = 0; i < key.length(); i++) {
            result.append(key.charAt(i));//添加字母
            combination(digits,index+1);//递归添加下一位字母
            result.deleteCharAt(result.length()-1);//删除添加的字母，避免对循环产生影响
        }
    }
}
