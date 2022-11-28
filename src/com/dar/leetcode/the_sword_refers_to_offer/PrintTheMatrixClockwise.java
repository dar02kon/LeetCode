package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.Arrays;

/**
 * @author :wx
 * @description : 剑指 Offer 29. 顺时针打印矩阵 https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/?favorite=xb9nqhhg
 * @create :2022-11-28 10:22:00
 */
public class PrintTheMatrixClockwise {

    public static void main(String[] args) {
        int[][] n = {{1},{5},{9}};
        int[] ints = new PrintTheMatrixClockwise().spiralOrder(n);
        System.out.println(Arrays.toString(ints));
    }

    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0){
            return new int[0];
        }

        int[] result = new int[matrix.length* matrix[0].length];
        int upperBound = 0;//上边界
        int lowerBound = matrix.length-1;//下边界
        int leftSideBoundary = 0;//左边界
        int rightSideBoundary = matrix[0].length-1;//右边界
        int left=0,right=0;//坐标
        int direction =matrix[0].length==1?1:0;//移动方向
        int i=0;
        while (i != result.length) {
            result[i++] = matrix[left][right];
            switch (direction) {//确定移动方向
                case 0://向右移动
                    right++;
                    if (right == rightSideBoundary) {//到达右边界
                        upperBound++;//修改上边界
                        direction = 1;//下一步向下移动
                    }
                    break;
                case 1://向下移动
                    left++;
                    if (left == lowerBound) {//到达下边界
                        rightSideBoundary--;//修改右边界
                        direction = 2;//下一步向左移动
                    }
                    break;
                case 2://向左移动
                    right--;
                    if (right == leftSideBoundary) {//达到左边界
                        lowerBound--;//修改下边界
                        direction = 3;//下一步向上移动
                    }
                    break;
                case 3://向上移动
                    left--;
                    if (left == upperBound) {//达到上边界
                        leftSideBoundary++;//修改左边界
                        direction = 0;//下一步向右移动
                    }
                    break;
            }
        }

        return result;
    }

}
