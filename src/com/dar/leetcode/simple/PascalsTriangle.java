package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 杨辉三角 https://leetcode.cn/problems/pascals-triangle/
 * @create :2022-09-30 19:34:00
 */
public class PascalsTriangle {

    public static void main(String[] args) {
        List<List<Integer>> lists = new PascalsTriangle().generate(5);
        for (List<Integer> list : lists) {
            for (Integer integer : list) {
                System.out.print(integer+" ");
            }
            System.out.println();
        }
    }

    /**
     * 直接遍历填充
     * @param numRows
     * @return
     */
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> list = new ArrayList<>(numRows);
        for(int i=0;i<numRows;i++){
            List<Integer> list1 = new ArrayList<>(i+1);
            for (int j = 0; j < i+1 ; j++) {
                if(j==0||j==i){
                    list1.add(1);
                } else {
                    int i1 = list.get(i - 1).get(j - 1) + list.get(i - 1).get(j);
                    list1.add(i1);
                }
            }
            list.add(list1);
        }
        return list;
    }
}
