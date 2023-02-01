package com.dar.leetcode.algorithm_training.dynamic_programming;

import java.util.List;

/**
 * @author :wx
 * @description : 139. 单词拆分 https://leetcode.cn/problems/word-break/
 * @create :2023-02-01 19:14:00
 */
public class WordBreak {

    public static void main(String[] args) {

    }

    public boolean wordBreak(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()+1];
        dp[0] = true;
        for (int i = 1; i <= s.length() ; i++) {
            for (String word : wordDict) {//遍历字典
                if(word.length()<=i&&s.substring(i-word.length(),i).equals(word)){
                    dp[i] = dp[i]||dp[i-word.length()];//判断前一部分能否被表示
                }
            }
        }
        return dp[s.length()];
    }
}
