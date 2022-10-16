package com.dar.leetcode.medium;

/**
 * @author :wx
 * @description : 整数转罗马数字 https://leetcode.cn/problems/integer-to-roman/
 * @create :2022-10-16 10:16:00
 */
public class IntegerToRoman {

    public static void main(String[] args) {
        System.out.println(new IntegerToRoman().intToRoman(10));
    }

    /**
     * 偏向于暴力的解法（这么写太丑陋了哈哈哈哈）
     * @param num
     * @return
     */
    public String intToRoman(int num) {
        StringBuilder result = new StringBuilder();
        int count = 1;
        while (num!=0){
            result.insert(0, getChar(num % 10, count));
            count++;
            num /= 10;
        }
        return result.toString();
    }
    public String getChar(int num, int count){
        switch (count){
            case 1:
                if(num>0&&num<=3){
                    StringBuilder result= new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        result.append("I");
                    }
                    return result.toString();
                } else if(num==4){
                    return "IV";
                } else if(num<=8&&num>4){
                    StringBuilder result= new StringBuilder("V");
                    for (int i = 0; i < num-5; i++) {
                        result.append("I");
                    }
                    return result.toString();
                } else if(num==9){
                    return "IX";
                } else {
                    return "";
                }
            case 2:
                if(num>0&&num<=3){
                    StringBuilder result= new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        result.append("X");
                    }
                    return result.toString();
                } else if(num==4){
                    return "XL";
                } else if(num<=8&&num>4){
                    StringBuilder result= new StringBuilder("L");
                    for (int i = 0; i < num-5; i++) {
                        result.append("X");
                    }
                    return result.toString();
                } else if(num==9){
                    return "XC";
                } else {
                    return "";
                }
            case 3:
                if(num>0&&num<=3){
                    StringBuilder result= new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        result.append("C");
                    }
                    return result.toString();
                } else if(num==4){
                    return "CD";
                } else if(num<=8&&num>4){
                    StringBuilder result= new StringBuilder("D");
                    for (int i = 0; i < num-5; i++) {
                        result.append("C");
                    }
                    return result.toString();
                } else if(num==9){
                    return "CM";
                } else {
                    return "";
                }
            case 4:
                if(num>0&&num<=3){
                    StringBuilder result= new StringBuilder();
                    for (int i = 0; i < num; i++) {
                        result.append("M");
                    }
                    return result.toString();
                } else {
                    return "";
                }
            default:
                return "";
        }
    }

    /**
     * 也可以先把对应位上的字母保存到数组中
     * @param num
     * @return
     */
    String[] thousands = {"", "M", "MM", "MMM"};
    String[] hundreds  = {"", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM"};
    String[] tens      = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
    String[] ones      = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
    public String intToRoman2(int num) {
        StringBuffer roman = new StringBuffer();
        roman.append(thousands[num / 1000]);
        roman.append(hundreds[num % 1000 / 100]);
        roman.append(tens[num % 100 / 10]);
        roman.append(ones[num % 10]);
        return roman.toString();
    }


    /**
     * 模拟
     * 寻找不超过num的最大符号值，将num减去该符号值，
     * 然后继续寻找不超过 num 的最大符号值，将该符号拼接在上一个找到的符号之后，
     * 循环直至num为0。最后得到的字符串即为num的罗马数字表示。
     */
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman3(int num) {
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            int value = values[i];
            String symbol = symbols[i];
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }

}
