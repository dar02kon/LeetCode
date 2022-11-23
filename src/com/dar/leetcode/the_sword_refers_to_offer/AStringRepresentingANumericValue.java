package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 剑指 Offer 20. 表示数值的字符串 https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-23 11:26:00
 */
public class AStringRepresentingANumericValue {

    public static void main(String[] args) {
        boolean number = new AStringRepresentingANumericValue().isNumber2(" 1 4");
        System.out.println(number);
    }

    /**
     * 常规思路：if else...
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        boolean pointCount = false;//判断 .出现,出现改为true
        boolean flag = false;//判断E/e是否出现
        boolean target = false;//判断E/e的出现是否合法，E/e只能出现在小数点或者数字后面
        s = s.trim();//去掉前后空格
        for (int i = 0; i < s.length(); i++) {//遍历
            char c = s.charAt(i);
            if (c == ' ') {//中间出现空格，则不能表示数值
                return false;
            }
            if (c == '+' || c == '-') {//正负符号
                //判断正负符号出现的位置是否合法，在第一个有效位置或者在E/e前
                if (!(i == 0 || (s.charAt(i - 1) == 'E' || s.charAt(i - 1) == 'e'))) {
                    return false;
                }
                //判断正负符号的使用是否合法，其后必须有数字
                if (!(i < s.length() - 1 && (s.charAt(i + 1) >= '0' || s.charAt(i + 1) <= '9'))) {
                    return false;
                }
            } else {
                if (c < '0' || c > '9') {//非数字符号
                    if (flag) {//已出现E/e，其后只能跟着一个整数
                        return false;
                    }
                    if (c == '.') {//小数点
                        if (pointCount) {//小数点只能出现一次
                            return false;
                        }
                        //判断小数点的使用是否合法，小数点的前面或者后面至少有一个数字
                        if (!((i > 0 && (s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9')) || (i < s.length() - 1 && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')))) {
                            return false;
                        }
                        pointCount = true;//标记小数点已出现
                        target = true;//允许E/e出现
                    } else if (c == 'e' || c == 'E') {//E/e
                        if (!target || i == s.length() - 1) {//出现条件不符合，即前面即没有数字也没有小数点，或者出现在最后一个位置
                            return false;
                        }
                        flag = true;//标记E/e已出现
                    } else {
                        return false;//出现了奇怪的字符
                    }
                } else {
                    target = true;//允许E/e出现
                }
            }
        }
        return 0 != s.length();
    }

    public boolean isNumber2(String s) {
        Map[] transfer = {
                new HashMap<Character, Integer>() {{//状态0接受字符
                    put('b',0);
                    put('n',2);
                    put('p',4);
                    put('s',1);
                }},
                new HashMap<Character, Integer>(){{//状态1接受字符
                    put('p',4);
                    put('n',2);
                }},
                new HashMap<Character, Integer>(){{//状态2接受字符
                    put('n',2);
                    put('e',6);
                    put('p',3);
                    put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态3接受字符
                   put('n',5);
                   put('e',6);
                   put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态4接受字符
                    put('n',5);
                }},
                new HashMap<Character, Integer>(){{//状态5接受字符
                   put('n',5);
                   put('e',6);
                   put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态6接受字符
                   put('n',8);
                   put('s',7);
                }},
                new HashMap<Character, Integer>(){{//状态7接受字符
                    put('n',8);
                }},
                new HashMap<Character, Integer>(){{//状态8接受字符
                    put('n',8);
                    put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态9接受字符
                   put('b',9);
                }}
        };
        int state = 0;//初始状态
        char t;
        for (char c : s.toCharArray()) {
            if(c>='0'&&c<='9'){
                t = 'n';//接受数字
            } else if(c=='.'){
                t = 'p';//接受小数点
            } else if(c=='E'||c=='e'){
                t = 'e';//接受E/e
            } else if(c==' '){
                t= 'b';//接受空格
            } else if(c=='+'||c=='-'){
                t = 's';//接受正负号
            } else {
                return false;
            }
            if(!transfer[state].containsKey(t)){//若此状态不能接受该字符，则直接返回false
                return false;
            }
            state = (int)transfer[state].get(t);//状态转移
        }
        return state==2||state==3||state==5||state==8||state==9;//2，3，5，8，9是可以数值的状态
    }

}
