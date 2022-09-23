package com.dar.leetcode.simple;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 加一 https://leetcode.cn/problems/plus-one/
 * @create :2022-09-23 16:03:00
 */
public class PlusOne {

    public static void main(String[] args) {
        int [] nums = {9,9,9};
        System.out.println(Arrays.toString(new PlusOne().plusOne(nums)));
    }

    /**
     * 设置一个count作为进位计数，初始化为1（因为一开始需要加1）
     * 数组元素加1后为10，则继续遍历，不为10，则直接返回
     *
     * 数组遍历完说明数组是形如999的数字（数组位数不够），
     * 再创建一个length+1的数组，首位为1，其余位为0，返回该数组
     * @param digits
     * @return
     */
    public int[] plusOne(int[] digits) {
        int count = 1;
        for (int i = digits.length-1; i > -1; i--) {
            digits[i] = digits[i]+count;
            if(digits[i]==10){
                digits[i]=0;
            } else {
                return digits;
            }
        }
        int [] result = new int[digits.length+1];
        Arrays.fill(result,0);
        result[0] = 1;
        return result;
    }

    /**
     * 优化一下：
     * 取消没有必要的变量count，直接元素++就可以了
     * 不需要Arrays.fill(result,0);为数组赋值（new创建int类型的数组默认为0）
     * @param digits
     * @return
     */
    public int[] plusOne2(int[] digits) {
        for (int i = digits.length-1; i > -1; i--) {
            digits[i] = ++digits[i];//++要放在元素前面，不然赋值没有效果
            if(digits[i]==10){
                digits[i]=0;
            } else {
                return digits;
            }
        }
        int [] result = new int[digits.length+1];//默认为0
        result[0] = 1;
        return result;
    }
}
