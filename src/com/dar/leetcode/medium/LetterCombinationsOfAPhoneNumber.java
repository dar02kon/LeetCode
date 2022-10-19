package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author :wx
 * @description : 电话号码的字母组合 https://leetcode.cn/problems/letter-combinations-of-a-phone-number/
 * @create :2022-10-19 16:02:00
 */
public class LetterCombinationsOfAPhoneNumber {

    public static void main(String[] args) {
        List<String> list = new LetterCombinationsOfAPhoneNumber().letterCombinations2("5678");
        for (String s : list) {
            System.out.println(s);
        }
    }

    static Map<Character,String> map = new HashMap<>();
    static {
        map.put('2',"abc");
        map.put('3',"def");
        map.put('4',"ghi");
        map.put('5',"jkl");
        map.put('6',"mno");
        map.put('7',"pqrs");
        map.put('8',"tuv");
        map.put('9',"wxyz");
    }

    /**
     * “回去等通知吧”（太丑陋了）
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if(digits.length()>0){
            String s1 = map.get(digits.charAt(0));
            for (int i = 0;i<s1.length(); i++) {
                if(digits.length()>1){
                    String s2 = map.get(digits.charAt(1));
                    for (int j = 0;j < s2.length(); j++) {
                        if(digits.length()>2){
                            String s3 = map.get(digits.charAt(2));
                            for (int k = 0; k < s3.length() ; k++) {
                                if(digits.length()>3){
                                    String s4 = map.get(digits.charAt(3));
                                    for (int l = 0; l < s4.length(); l++) {
                                        list.add(""+s1.charAt(i)+s2.charAt(j)+s3.charAt(k)+s4.charAt(l));
                                    }
                                } else {
                                    list.add(""+s1.charAt(i)+s2.charAt(j)+s3.charAt(k));
                                }
                            }
                        } else {
                            list.add(""+s1.charAt(i)+s2.charAt(j));
                        }
                    }
                } else {
                    list.add(""+s1.charAt(i));
                }
            }
        } else {
            return list;
        }
        return list;
    }



    private StringBuilder stringBuilder2 = new StringBuilder();
    private List<String> list2 = new ArrayList<>();
    private String[] map2 = {"abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};

    /**
     * 递归回溯
     *
     * 上面这种抽象的解法中为什么要这么丑陋的使用if语句判断是否越界呢
     * 因为无法判断循环层数，即n层循环的遍历无法直接使用for来解决
     *
     * 但仔细观察这一题是不是有点像树从第一层到最后一层有多少条路径
     *      例如"23"
     *          a          b          c
     *       d  e  f    d  e  f    d  e  f
     * 所以可以使用递归来解决
     * 在记录节点值的时候我们最担心的是节点在返回后stringBuilder中会不会出现重复，
     * 所以在每一次回溯后我们都要删除stringBuilder最后一位字符
     * @param digits
     * @return
     */
    public List<String> letterCombinations2(String digits) {
        if(digits==null||digits.length()==0){//判空
            return null;
        }
        addChar(digits,0);
        return list2;
    }
    public void addChar(String digits,int index){//index可以理解为层数
        if(digits.length()==index){
            list2.add(stringBuilder2.toString());//已经走过最后一层了，添加字符串后回溯
            return;
        }
        int num = digits.charAt(index) - '0';//获取该层对应字符串的索引
        String s = map2[num-2];//获取该层对应的字符串
        for (int i = 0; i < s.length(); i++) {//遍历每一层的字符
            stringBuilder2.append(s.charAt(i));//添加字符
            addChar(digits,index+1);//继续遍历下一层
            stringBuilder2.deleteCharAt(stringBuilder2.length()-1);//删除最后一位，防止在同一层出现重复
        }
    }
}
