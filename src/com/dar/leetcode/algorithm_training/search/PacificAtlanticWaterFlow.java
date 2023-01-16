package com.dar.leetcode.algorithm_training.search;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 417. 太平洋大西洋水流问题 https://leetcode.cn/problems/pacific-atlantic-water-flow/description/
 * @create :2023-01-16 15:15:00
 */
public class PacificAtlanticWaterFlow {

    public static void main(String[] args) {

    }

    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> list = new ArrayList<>();
        if (heights == null || heights.length == 0) return list;
        int m = heights.length;
        int n = heights[0].length;
        boolean[][] reachP = new boolean[m][n];
        boolean[][] reachA = new boolean[m][n];
        for (int i = 0; i < m; i++) {
            dfs(i, 0, heights, reachP);//从左边流入太平洋
            dfs(i, n - 1, heights, reachA);//从右边流入大西洋
        }

        for (int i = 0; i < n; i++) {
            dfs(0, i, heights, reachP);//从上边流入太平洋
            dfs(m - 1, i, heights, reachA);//从下边流入大西洋
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (reachA[i][j] && reachP[i][j]) {//求交集
                    List<Integer> temp = new ArrayList<>();
                    temp.add(i);
                    temp.add(j);
                    list.add(temp);
                }
            }
        }
        return list;
    }

    private final int[][] direction = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public void dfs(int i, int j, int[][] heights, boolean[][] flag) {
        if (flag[i][j]) return;
        flag[i][j] = true;
        for (int[] num : direction) {//上下左右移动
            int preI = i + num[0];
            int preJ = j + num[1];
            if (preI < 0 || preJ < 0 || preI >= heights.length || preJ >= heights[0].length || heights[preI][preJ] < heights[i][j]) {
                continue;
            }
            dfs(preI, preJ, heights, flag);
        }
    }
}
