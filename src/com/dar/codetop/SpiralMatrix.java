package com.dar.codetop;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 54. 螺旋矩阵 https://leetcode.cn/problems/spiral-matrix/description/
 * @create :2023-07-08 19:56:00
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int left = 0,right = matrix[0].length-1,top = 0,bottom = matrix.length-1;
        List<Integer> list = new ArrayList<>();
        while (left<=right&&top<=bottom){
            // 从左往右
            for (int i = left; i <= right; i++) {
                list.add(matrix[top][i]);
            }
            // 从上往下
            for (int i = top+1; i <= bottom ; i++) {
                list.add(matrix[i][right]);
            }
            if(left<right&&top<bottom){
                // 从右往左
                for (int i = right-1; i >= left ; i--) {
                    list.add(matrix[bottom][i]);
                }
                // 从下往上
                for (int i = bottom-1; i > top ; i--) {
                    list.add(matrix[i][left]);
                }
            }
            // 内层
            left++;
            right--;
            top++;
            bottom--;
        }
        return list;
    }
}
