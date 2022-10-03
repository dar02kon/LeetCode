package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : Excel表列名称 https://leetcode.cn/problems/excel-sheet-column-title/
 * @create :2022-10-03 20:08:00
 */
public class ExcelSheetColumnTitle {


    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnTitle().convertToTitle(28));
    }

    /**
     * 不能直接当作26进制来处理,每一位的值最小为A（1），最大为Z（26），一共可以有26个数
     * 每次取最低位num = columnNumber%26，如果num为0，则赋num值为26，因为26是允许存在的
     * 每一次取余后我们都多加了一个数，所以需要columnNumber-1再除以26
     *
     * @param columnNumber
     * @return
     */
    public String convertToTitle(int columnNumber) {
        StringBuilder s = new StringBuilder();
        while (columnNumber > 0) {
            int num = columnNumber % 26;
            if (num == 0) {
                num = 26;
            }
            s.insert(0, (char) ('A' + num - 1));
            columnNumber = (columnNumber - 1) / 26;
        }
        return s.toString();
    }

    /**
     * 这种写法更清晰：
     * 类似于[1,26] -1 = [0,25]
     * @param columnNumber
     * @return
     */
    public String convertToTitle2(int columnNumber) {
        StringBuilder sb = new StringBuilder();
        while (columnNumber != 0) {
            columnNumber--;
            sb.append((char)(columnNumber % 26 + 'A'));
            columnNumber /= 26;
        }
        return sb.reverse().toString();
    }


}
