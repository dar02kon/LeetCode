package com.dar.leetcode.algorithm_training.backtrack;

/**
 * @author :wx
 * @description : 79. 单词搜索 https://leetcode.cn/problems/word-search/
 * @create :2023-01-17 19:39:00
 */
public class WordSearch {

    public static void main(String[] args) {

    }

    int[][] direction = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};//四个方向
    int len = 0;//记录拼接的字符串长度
    boolean[][] flag;

    public boolean exist(char[][] board, String word) {
        flag = new boolean[board.length][board[0].length];//标记数组
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {//挨个开始
                if (combination(i, j, board, word)) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean combination(int i, int j, char[][] bord, String word) {
        if (len == word.length()) {
            return true;
        }
        //越界判断与重复判断
        if (i < 0 || j < 0 || i >= bord.length || j >= bord[0].length || flag[i][j]) return false;
        //字母不符合要求
        if (word.charAt(len) != bord[i][j]) return false;
        len++;//拼接一个字母
        flag[i][j] = true;//标记访问
        boolean temp;
        for (int[] num : direction) {
            //递归判断下一个字母
            temp = combination(i + num[0], j + num[1], bord, word);
            if (temp) {
                return true;
            }
        }
        flag[i][j] = false;//取消标记
        len--;//长度减一
        return false;
    }
}
