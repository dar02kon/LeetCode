package com.dar.leetcode.algorithm_training.search;

/**
 * @author :wx
 * @description : 200. 岛屿数量 https://leetcode.cn/problems/number-of-islands/
 * @create :2023-01-15 09:10:00
 */
public class NumberOfIsLands {

    public static void main(String[] args) {

    }

    public int numIslands(char[][] grid) {
        int result = 0;
        for (int i = 0; i <grid.length ; i++) {
            for (int j = 0; j <grid[0].length ; j++) {
                if(grid[i][j]=='1'){
                    dfs(grid,i,j);
                    result++;
                }
            }
        }
        return result;
    }

    public void dfs(char[][] grid,int i,int j){
        if(i<0||j<0||i>=grid.length||j>=grid[0].length||grid[i][j]=='0') return;
        grid[i][j] ='0';
        dfs(grid,i-1,j);
        dfs(grid,i+1,j);
        dfs(grid,i,j-1);
        dfs(grid,i,j+1);
    }
}
