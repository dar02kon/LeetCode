package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 46. 把数字翻译成字符串 https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-17 19:40:00
 */
public class TranslateNumbersIntoStrings {

    public static void main(String[] args) {
        int num = new TranslateNumbersIntoStrings().translateNum(122224);
        System.out.println(num);
    }

    public int translateNum(int num) {
        int record1 = 1;
        int record2 = 1;
        if(num<10){
            return 1;
        }
        char[] array = (num + "").toCharArray();
        for (int i = 1; i < array.length; i++) {
            if(array[i-1]!='0'&&(array[i-1]-'0')*10+(array[i]-'0')<26){
                int flag = record2;
                record2 = record1+record2;
                record1 = flag;
            } else {
                record1 = record2;
            }

        }
        return record2;
    }
}
