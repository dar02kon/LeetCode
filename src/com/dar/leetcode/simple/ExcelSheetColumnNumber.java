package com.dar.leetcode.simple;

/**
 * @author :wx
 * @description : Excel 表列序号 https://leetcode.cn/problems/excel-sheet-column-number/
 * @create :2022-10-04 19:35:00
 */
public class ExcelSheetColumnNumber {

    public static void main(String[] args) {
        System.out.println(new ExcelSheetColumnNumber().titleToNumber2("ZY"));
    }

    /**
     * 从后往前拆分
     * @param columnTitle
     * @return
     */
    public int titleToNumber(String columnTitle) {
        int num = 1;
        int result = 0;
        for (int i = columnTitle.length()-1; i >= 0; i--) {
            result +=(columnTitle.charAt(i)-'A'+1)*num;
            num *= 26;
        }
        return result;
    }

    /**
     * 从前往后拆分
     * @param columnTitle
     * @return
     */
    public int titleToNumber2(String columnTitle) {
        int result = 0;
        for (int i = 0; i < columnTitle.length() ; i++) {
            result = result*26 + (columnTitle.charAt(i)-'A'+1);
        }
        return result;
    }
}
