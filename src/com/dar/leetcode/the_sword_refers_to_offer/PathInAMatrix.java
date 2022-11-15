package com.dar.leetcode.the_sword_refers_to_offer;

/**
 * @author :wx
 * @description : 剑指 Offer 12. 矩阵中的路径 https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/description/
 * @create :2022-11-15 18:37:00
 */
public class PathInAMatrix {
    public static void main(String[] args) {
        char[][] nums = {{'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}};
        boolean exist = new PathInAMatrix().exist(nums, "ABCCED");
        System.out.println(exist);
    }

    boolean[][] visited;//标记数组
    boolean flag = false;//最终结果

    /**
     * 深搜
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                route(board, i, j, word, 0);//以每一个元素为起点去深搜
            }
        }
        return flag;
    }

    public void route(char[][] board, int x, int y, String word, int index) {
        if (index == word.length()) {//找到了字符串
            flag = true;
            return;
        }
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || visited[x][y] || board[x][y] != word.charAt(index)) {//不符合要求的情况
            return;
        }
        visited[x][y] = true;//标记访问
        route(board, x + 1, y, word, index + 1);//下移，继续递归
        route(board, x, y + 1, word, index + 1);//右移，继续递归
        route(board, x - 1, y, word, index + 1);//上移，继续递归
        route(board, x, y - 1, word, index + 1);//左移，继续递归
        visited[x][y] = false;//清除标记
    }


}
