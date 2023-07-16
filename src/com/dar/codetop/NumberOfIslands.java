package com.dar.codetop;

/**
 * @author :wx
 * @description : 200. 岛屿数量 https://leetcode.cn/problems/number-of-islands/description/
 * @create :2023-07-16 16:44:00
 */
public class NumberOfIslands {
    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1') {
                    dfs(grid, i, j);
                    result++;
                }
            }
        }
        return result;
    }

    /**
     * 从一点开始去遍历它四周的节点，将遍历的节点标记为0
     */
    private void dfs(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == '0') {
            return;
        }
        grid[i][j] = '0';
        dfs(grid, i - 1, j);
        dfs(grid, i + 1, j);
        dfs(grid, i, j - 1);
        dfs(grid, i, j + 1);
    }
}
