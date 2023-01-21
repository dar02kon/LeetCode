package com.dar.leetcode.algorithm_training.backtrack;

/**
 * @author :wx
 * @description : 37. 解数独 https://leetcode.cn/problems/sudoku-solver/
 * @create :2023-01-21 15:58:00
 */
public class SudokuSolver {

    public static void main(String[] args) {

    }

    boolean[][] rowMark = new boolean[9][10];//标记行
    boolean[][] colMark = new boolean[9][10];//标记列
    boolean[][] areaMark = new boolean[9][10];//标记矩阵块
    char[][] board;

    public void solveSudoku(char[][] board) {
        this.board = board;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int num = board[i][j] - '0';
                    rowMark[i][num] = true;
                    colMark[j][num] = true;
                    areaMark[mapping(i, j)][num] = true;
                }
            }
        }
        fill(0, 0);//开始填充
    }

    public boolean fill(int i, int j) {
        while (i < 9 && board[i][j] != '.') {//从左往右，从上往下
            i = j == 8 ? i + 1 : i;
            j = j == 8 ? 0 : j + 1;
        }
        if (i == 9) {//已经填充完了
            return true;
        }
        for (int num = 1; num <= 9; num++) {//更换数字进行填充
            if (rowMark[i][num] || colMark[j][num] || areaMark[mapping(i, j)][num]) continue;//数字不符合要求
            //标记
            rowMark[i][num] = colMark[j][num] = areaMark[mapping(i, j)][num] = true;
            board[i][j] = (char) (num + '0');
            if (fill(i, j)) return true;//为true则说明填充完成
            //取消标记
            board[i][j] = '.';
            rowMark[i][num] = colMark[j][num] = areaMark[mapping(i, j)][num] = false;
        }
        return false;
    }

    public int mapping(int i, int j) {//映射到对应的九宫格内 从左往右，从上往下
        i /= 3;
        j /= 3;
        return i * 3 + j;
    }
}
