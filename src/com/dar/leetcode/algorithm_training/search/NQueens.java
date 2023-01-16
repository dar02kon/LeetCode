package com.dar.leetcode.algorithm_training.search;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 51. N 皇后 https://leetcode.cn/problems/n-queens/description/
 * @create :2023-01-16 16:25:00
 */
public class NQueens {

    public static void main(String[] args) {

    }

    char[][] queens;
    boolean[] columnMarker;//列标记
    boolean[] rightDiagonalMarker;//正对角线标记（135°）
    boolean[] inverseDiagonalMarker;//反对角线标记（45°）
    List<List<String>> list;

    public List<List<String>> solveNQueens(int n) {
        list = new ArrayList<>();
        columnMarker = new boolean[n];
        //对角线正反各2 * n - 1条
        rightDiagonalMarker = new boolean[2 * n - 1];
        inverseDiagonalMarker = new boolean[2 * n - 1];
        queens = new char[n][n];
        for (char[] queen : queens) {//初始化棋盘
            Arrays.fill(queen,'.');
        }
        search(0,n);//从第一行开始填充
        return list;
    }

    public void search(int row, int n) {
        if (row == n) {//最后一行已经有一个皇后了
            List<String> temp = new ArrayList<>();
            for (char[] queen : queens) {
                temp.add(new String(queen));
            }
            list.add(temp);
            return;
        }

        for (int col = 0; col < n; col++) {//每一列
            int rightDiagonal = n - 1 - (row - col);//映射到135°对角线的下标
            int inverseDiagonal = row + col;//映射到45°对角线的下标
            if (columnMarker[col] || rightDiagonalMarker[rightDiagonal] || inverseDiagonalMarker[inverseDiagonal])
                continue;//不符合要求
            //标记
            columnMarker[col] = rightDiagonalMarker[rightDiagonal] = inverseDiagonalMarker[inverseDiagonal] = true;
            queens[row][col] = 'Q';
            //继续填充下一行
            search(row + 1, n);
            //回溯，取消标记
            columnMarker[col] = rightDiagonalMarker[rightDiagonal] = inverseDiagonalMarker[inverseDiagonal] = false;
            queens[row][col] = '.';
        }
    }
}
