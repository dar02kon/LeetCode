package com.dar.leetcode.simple;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 同构字符串 https://leetcode.cn/problems/isomorphic-strings/
 * @create :2022-10-07 15:56:00
 */
public class IsomorphicStrings {

    public static void main(String[] args) {
        System.out.println(new IsomorphicStrings().isIsomorphic("abc","ggl"));
    }

    /**
     * 基本思路：使用hashMap来存储对应关系
     * 这是一个双向奔赴的过程，不应该存在相同key值的同时也不应该存在相同的value
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        Map<Character,Character> map = new HashMap<>();
        for (int i = 0; i < s.length() ; i++) {
            if(map.containsKey(s.charAt(i))){
                if(map.get(s.charAt(i))!=t.charAt(i)){
                    return false;
                }
            } else{
                if(map.containsValue(t.charAt(i))){
                    return false;
                }
                map.put(s.charAt(i),t.charAt(i));
            }
        }
        return true;
    }

    /**
     * 比较第一次出现的索引
     * 取当前位置字符第一次出现的索引进行对比
     * 从左往右遍历
     * 两个字符串中的对应字符如果是第一次出现，他们的索引位置肯定相同，
     * 继续往后遍历，查询当前位置字符第一次出现的索引就相当于从记录对应关系的map中取值
     * （可以发现上面map中key，value的值肯定与两字符串中的两个字符第一次出现的对应关系相同）
     *  例如（对应是双向的）：
     *      字符串："paper"  "title"
     *      遍历map：
     *      p 对应 t
     *      a 对应 i
     *      r 对应 e
     *      e 对应 l
     *      字符串："foo"   "bar"
     *      遍历map：
     *      f 对应 b
     *      o 对应 a
     *      字符串："abc"   "ggl"
     *      遍历map：
     *      a 对应 g
     *
     * 如果某个字符已经出现过，则在它第一次出现的时候它对应位置的字符就已经确定了，
     * 直接通过indexOf()查询第一次出现的位置，不相等，则他们肯定不是同构字符串
     *
     * 情况1(一个字符映射多个): foo 与 bar, 处理第二个o时发现索引不同
     * 情况2(多个字符映射同个): abc 与 ggl, 处理第二个g时发现索引不同
     * @param s
     * @param t
     * @return
     */
    public boolean isIsomorphic2(String s, String t) {
        if(s.length()!=t.length()){
            return false;
        }
        for (int i = 0; i < s.length(); i++) {
            if (s.indexOf(s.charAt(i)) != t.indexOf(t.charAt(i))){
                return false;
            }
        }
        return true;

    }
}
