package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 60. n个骰子的点数 https://leetcode.cn/problems/nge-tou-zi-de-dian-shu-lcof/description/?favorite=xb9nqhhg
 * @create :2022-12-31 11:15:00
 */
public class TheRollOfNDice {

    public static void main(String[] args) {

    }

    public double[] dicesProbability(int n) {
        double[] result = new double[6];
        Arrays.fill(result,1.0/6);//1个骰子
        for (int i = 2; i <=n ; i++) {
            double[] temp = new double[5*i+1];//5*i+1为i个骰子数字和的所有情况，即6n-n+1
            for (int j = 0; j < result.length; j++) {
                for (int k = 0; k <6 ; k++) {//新加的1个骰子，0-5对应1-6
                    temp[j+k] += result[j]/6.0;//1/6.0是新加的那个骰子对应数字出现的概率
                }
            }
            result = temp;//更新结果
        }
        return result;
    }
}
