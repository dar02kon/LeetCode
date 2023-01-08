package com.dar.leetcode.algorithm_training.greedy_thinking;

/**
 * @author :wx
 * @description : 605. 种花问题 https://leetcode.cn/problems/can-place-flowers/description/
 * @create :2023-01-08 11:08:00
 */
public class CanPlaceFlowers {

    public static void main(String[] args) {

    }

    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        for (int i = 0; i < flowerbed.length; i++) {
            //判断插花条件是否满足
            if(flowerbed[i] == 0 && ((i == 0 || flowerbed[i - 1] == 0)&&(i==flowerbed.length-1||flowerbed[i+1]==0))) {
                flowerbed[i] = 1;//插花
                n--;//花的数量减1
            }
        }
        return n<=0;//判断花是否插完
    }
}
