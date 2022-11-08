package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 04. 二维数组中的查找 https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/description/?favorite=xb9nqhhg
 * @create :2022-11-08 10:07:00
 */
public class LookupInATwoDimensionalArray {

    public static void main(String[] args) {
//        int[][] nums = {
//                {1, 4, 7, 11, 15},
//                {2, 5, 8, 12, 19},
//                {3, 6, 9, 16, 22},
//                {10, 13, 14, 17, 24},
//                {18, 21, 23, 26, 30}
//        };
        int[][] nums = {{1}, {3}, {5}, {6}};
        boolean numberIn2DArray = new LookupInATwoDimensionalArray().findNumberIn2DArray2(nums, 6);
        System.out.println(numberIn2DArray);
    }

    /**
     * 二分查找
     * 可以先利用二分查找先找出可能存在目标值的行数范围
     * 再在对应的行中寻找第一个>=目标值的下标，
     * 若等于目标值返回true，若大于目标值，则可以缩短下次二分查找的右边界
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) {
            return false;
        }
        // 寻找矩阵第一列第一个小于或者等于目标值的下标n
        // 从0-n行从首部来看可能存在目标值
        int left = -1;
        int right = matrix.length - 1;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (matrix[mid][0] <= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        int n = left;// 上界
        // 寻找矩阵最后一列第一个大于或者等于目标值的下标m
        // 从m-matrix.length-1行从尾部来看可能存在目标值
        left = 0;
        right = matrix.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (matrix[mid][matrix[0].length - 1] >= target) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        int m = right;// 下界
        if (n < m) {// 不存在目标值
            return false;
        }
        // 以m为起始行依次二分查找至第n行
        // 寻找每一行行中第一个大于或者等于目标值的下标，
        // 若等于则返回true，大于缩小下次查找的右边界
        int len = matrix[0].length;//列数
        for (int i = m; i <= n; i++) {
            left = 0;
            right = len;
            while (left < right) {
                int mid = (left + right) / 2;
                if (matrix[i][mid] >= target) {
                    right = mid;
                } else {
                    left = mid + 1;
                }
            }
            if (matrix[i][right] == target) {//找到目标值
                return true;
            }
            len = right;//缩小右边界
        }
        return false;
    }

    /**
     * 根据特点进行搜索
     * 由于题目所给矩阵的特性可知，位于矩阵右上角的那一点，向左移，则会小于它，向下移动则会大于它（类似于二叉搜索树）
     * 我们可以从右上角那一点开始搜索，
     * 大于目标值则往左移动（不能往上移动，因为我们就是从那来的），小于目标值则往下移动（同样不能往右移动）
     * @param matrix
     * @param target
     * @return
     */
    public boolean findNumberIn2DArray2(int[][] matrix, int target) {
        int row = 0;//对应行数
        int column = matrix[0].length-1;//对应列数
        while (row<matrix.length&&column>=0){
            if(matrix[row][column]==target){
                return true;
            }
            if(matrix[row][column]>target){
                column--;//列数减一，向左移动
            } else {
                row++;//行数加一，向下移动
            }
        }
        return false;
    }


}
