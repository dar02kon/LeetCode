package com.dar.leetcode.algorithm_training.search;

/**
 * @author :wx
 * @description : 547. 省份数量 https://leetcode.cn/problems/number-of-provinces/description/
 * @create :2023-01-14 10:49:00
 */
public class NumberOfProvinces {

    public static void main(String[] args) {

    }

    public int findCircleNum(int[][] isConnected) {
        int result = 0;
        boolean[] visited = new boolean[isConnected.length];//一共只有n个城市
        for (int i = 0; i < isConnected.length ; i++) {
            if(!visited[i]){//这个城市没有访问过，对这个城市进行搜索
                dfs(isConnected,i,visited);
                result++;
            }
        }
        return result;
    }

    public void dfs(int[][] isConnected,int i,boolean[] visited){
        visited[i]=true;//标记为已访问
        for (int j = 0; j <isConnected.length ; j++) {
            if(isConnected[i][j]==1&&!visited[j]){//对有关联的城市进行搜索
                dfs(isConnected,j,visited);
            }
        }
    }
}
