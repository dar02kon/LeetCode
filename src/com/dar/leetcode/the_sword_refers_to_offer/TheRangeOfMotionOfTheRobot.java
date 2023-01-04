package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author :wx
 * @description : 面试题13. 机器人的运动范围 https://leetcode.cn/problems/ji-qi-ren-de-yun-dong-fan-wei-lcof/?favorite=xb9nqhhg
 * @create :2023-01-04 09:27:00
 */
public class TheRangeOfMotionOfTheRobot {

    public static void main(String[] args) {
        int count = new TheRangeOfMotionOfTheRobot().movingCount(3, 2, 17);
        System.out.println(count);
    }

    public int movingCount(int m, int n, int k) {
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{0,0});
        boolean[][] visited = new boolean[m][n];
        int count = 0;
        visited[0][0]=true;
        while (!queue.isEmpty()){
            int[] poll = queue.poll();
            int x = poll[0],y=poll[1];
            count++;
            if(x+1<m&&!visited[x+1][y]&&check(x+1,y,k)){//向右走
                queue.add(new int[]{x+1,y});
                visited[x+1][y] = true;
            }
            if(y+1<n&&!visited[x][y+1]&&check(x,y+1,k)){//向下走
                queue.add(new int[]{x,y+1});
                visited[x][y+1] = true;
            }
        }
        return count;
    }

    public boolean check(int a,int b,int k){//判断坐标位数和与k的关系
        int count = 0;
        while (a>0){
            count+=a%10;
            a/=10;
        }
        while (b>0){
            count+=b%10;
            b/=10;
        }
        return count <= k;
    }

    public int movingCount2(int m, int n, int k){
        boolean[][] visited = new boolean[m][n];
        visited[0][0] = true;
        int count = 1;
        for (int i = 0; i <m ; i++) {
            for (int j = 0; j <n ; j++) {
                if((i==0&&j==0)||check2(i)+check2(j)>k){
                    continue;
                }
                if(i-1>=0){
                    visited[i][j] |= visited[i-1][j];//从(i-1,j)向右移动抵达(i,j)
                }
                if(j-1>=0){
                    visited[i][j] |= visited[i][j-1];//从(i,j-1)向下移动抵达(i,j)
                }
                count+=visited[i][j]?1:0;
            }
        }
        return count;
    }
    public int check2(int a){
        int count = 0;
        while (a>0){
            count+=a%10;
            a/=10;
        }
        return count;
    }
}
