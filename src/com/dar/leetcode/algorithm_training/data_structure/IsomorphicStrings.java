package com.dar.leetcode.algorithm_training.data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 205. 同构字符串 https://leetcode.cn/problems/isomorphic-strings/description/
 * @create :2023-02-15 16:21:00
 */
public class IsomorphicStrings {

    public static void main(String[] args) {

    }

    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()) return false;
        Map<Character,Character> map = new HashMap<>(256);
        for (int i = 0; i < s.length() ; i++) {
            char c = s.charAt(i);
            if(map.containsKey(c)){ // 已存储映射关系
                if(map.get(c)!=t.charAt(i)) return false; // 不符合映射关系
            } else {
                if(map.containsValue(t.charAt(i))){ // 不同字符不能映射到同一个字符上
                    return false;
                }
                map.put(c,t.charAt(i)); // 添加映射
            }
        }
        return true;
    }


    public boolean isIsomorphic2(String s, String t) {
        if(s.length()!=t.length()) return false;
        int[] sc = new int[256];
        int[] tc = new int[256];
        for (int i = 0; i < s.length() ; i++) {
            if(sc[s.charAt(i)]!=tc[t.charAt(i)]) return false;
            sc[s.charAt(i)] = i+1;
            tc[t.charAt(i)] = i+1;
        }
        return true;
    }

}
