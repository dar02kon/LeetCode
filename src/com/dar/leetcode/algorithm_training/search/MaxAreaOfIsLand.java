package com.dar.leetcode.algorithm_training.search;

/**
 * @author :wx
 * @description : 695. 岛屿的最大面积 https://leetcode.cn/problems/max-area-of-island/
 * @create :2023-01-14 09:46:00
 */
public class MaxAreaOfIsLand {

    public static void main(String[] args) {

    }

    public int maxAreaOfIsland(int[][] grid) {
        int max = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {//挨个搜
                if (grid[i][j] == 1) max = Math.max(max, dfs(grid, i, j));
            }
        }
        return max;
    }

    public int dfs(int[][] grid, int i, int j) {
        int m = grid.length;//行
        int n = grid[0].length;//列
        if (i < 0 || j < 0 || i >= m || j >= n) return 0;//越界判断
        if(grid[i][j]==0) return 0;
        grid[i][j] = 0;//将已经访问过的1改为0，避免重复访问
        //递归搜索上下左右的岛屿+1，1为刚才访问的那块岛屿
        return dfs(grid, i - 1, j) + dfs(grid, i + 1, j) + dfs(grid, i, j - 1) + dfs(grid, i, j + 1)+1;
    }
}
