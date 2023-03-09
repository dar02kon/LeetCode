package com.dar.leetcode.algorithm_training.dynamic_programming;

/**
 * @author :wx
 * @description : 312. 戳气球 https://leetcode.cn/problems/burst-balloons/
 * @create :2023-03-09 09:47:00
 */
public class BurstBalloons {

    public static void main(String[] args) {

    }

    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] result = new int[n+2][n+2];
        int[] temp = new int[n+2]; // 前后增加两位，防止越界
        temp[0] = 1;
        temp[n+1] = 1;
        System.arraycopy(nums, 0, temp, 1, nums.length);
        for (int i = n-1; i >=0 ; i--) {
            for (int j = i+2; j <=n+1 ; j++) {
                for (int k = i+1; k <j ; k++) {
                    int sum = temp[i]*temp[j]*temp[k]+result[i][k]+result[k][j];
                    result[i][j] = Math.max(result[i][j],sum);
                }
            }
        }
        return result[0][n+1];
    }

}
