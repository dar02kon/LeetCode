package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 240. 搜索二维矩阵 II https://leetcode.cn/problems/search-a-2d-matrix-ii/description/
 * @create :2023-02-22 09:10:00
 */
public class SearchAMatrixII {

    public static void main(String[] args) {

    }

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return false;
        }
        int m = matrix.length, n = matrix[0].length;
        int row = 0, col = n - 1;
        while (row < m && col >= 0) {
            if (matrix[row][col] == target) {
                return true;
            } else if (matrix[row][col] < target) {
                row++;
            } else {
                col--;
            }
        }
        return false;
    }


}
