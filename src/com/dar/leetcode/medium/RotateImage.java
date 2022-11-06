package com.dar.leetcode.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 旋转图像 https://leetcode.cn/problems/rotate-image/
 * @create :2022-11-06 15:17:00
 */
public class RotateImage {

    public static void main(String[] args) {
//        int[][] nums = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
        int[][] nums = {{1,2},{3,4}};
        new RotateImage().rotate(nums);
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    /**
     * 整体的旋转可以分成一层一层的旋转
     * <p>
     *     例如：
     *     1 2 3             7 4 1
     *     4 5 6  旋转外层    8 5 2
     *     7 8 9             9 6 3
     *     内层只剩一个方块5，不用旋转
     *     可以单独出一个函数用于旋转一层
     *     每层都旋转后即为想要的结果
     *     对于每一层的旋转其实就是挪动位置，上面的移到右边，右边移到下边，下边移到左边，左边移到下边
     *     为了方便可以使用数组记录任意一边的值再进行移到
     * </p>
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length/2;//层数
        for (int i = 1; i <= n; i++) {//旋转每一层
            rotateALayer(matrix,i);
        }
    }
    public void rotateALayer(int[][] matrix, int leftVertical){
        int rightVertical = matrix.length - leftVertical;//该层最大下标
        leftVertical = leftVertical-1;//该层最小下标
        List<Integer> list = new ArrayList<>(rightVertical-leftVertical+1);//记录一边的数组
        for (int i = leftVertical; i <= rightVertical; i++) {//记录右边的值，如 (0,3) --> (3,3)
            list.add(matrix[i][rightVertical]);
        }
        for (int i = rightVertical; i >= leftVertical ; i--) {//将上边的值移动到右边，如 (0,0) --> (0,3)移动到(0,3)-->(3,3)
            matrix[i][rightVertical] = matrix[leftVertical][i];
        }

        //左上角的数不需要移动到右上角（前面已经移动过了）
        for (int i = leftVertical; i < rightVertical; i++) {//将左边的值移动到上边，如 (1,0)-->(3,0)移动到(2,0)-->(0,0)
            matrix[leftVertical][i] = matrix[rightVertical-i+leftVertical][leftVertical];
        }

        //左上角的值不需要移动到左上交（前面已经移动过了）
        for (int i = leftVertical+1; i <= rightVertical; i++) {//下边的值移动到左边，如 (2,1)-->(2,2)移动到(1,0)-->(2,0)
            matrix[i][leftVertical] = matrix[rightVertical][i];
        }

        for (int i = leftVertical; i < rightVertical ; i++) {//右边的值移动到下边，同样右下角的值不需要改变
            matrix[rightVertical][i] = list.get(list.size()-1-i+leftVertical);
        }
    }

    /**
     * 两次翻转
     * 水平反转与主对角线反转
     *
     * 90度 旋转之后 row, col = col, n-1-row,
     * 可以直接便利存储到新的矩阵中。
     * 由于题目要求不能使用新的矩阵来存放变换之后的数据，
     * 所以需要用对折+对角线对折来处理
     * 对折: row, col = n-1-row, col
     * 对角线: n-1-row, col = col, n-1-row
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        // 水平翻转
        for (int i = 0; i < n / 2; ++i) {
            for (int j = 0; j < n; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - i - 1][j];
                matrix[n - i - 1][j] = temp;
            }
        }
        // 主对角线翻转
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

}
