package com.dar.leetcode.algorithm_training.search;

/**
 * @author :wx
 * @description : 130. 被围绕的区域 https://leetcode.cn/problems/surrounded-regions/description/
 * @create :2023-01-15 10:41:00
 */
public class SurroundedRegions {

    public static void main(String[] args) {

    }

    public void solve(char[][] board) {
        if(board==null||board.length==0) return;
        int m = board.length;//行数
        int n = board[0].length;//列数
        for (int i = 0; i < m; i++) {
            dfs(board, i, 0);
            dfs(board, i, n - 1);
        }

        for (int i = 0; i < n; i++) {
            dfs(board, 0, i);
            dfs(board, m - 1, i);
        }

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'T') board[i][j] = 'O';
                else if (board[i][j] == 'O') board[i][j] = 'X';
            }
        }

    }

    public void dfs(char[][] board, int i, int j) {
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != 'O') return;
        board[i][j] = 'T';
        dfs(board, i - 1, j);
        dfs(board, i + 1, j);
        dfs(board, i, j - 1);
        dfs(board, i, j + 1);
    }

}
