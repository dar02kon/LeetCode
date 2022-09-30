package com.dar.leetcode.simple;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :wx
 * @description : 杨辉三角 II  https://leetcode.cn/problems/pascals-triangle-ii/
 * @create :2022-09-30 20:06:00
 */
public class PascalsTriangleIi {

    public static void main(String[] args) {
        List<Integer> row = new PascalsTriangleIi().getRow2(10);
        for (Integer integer : row) {
            System.out.print(integer+" ");
        }
    }

    public List<Integer> getRow(int rowIndex) {
        int[] nums = new int[rowIndex+1];
        List<Integer> list = new ArrayList<>();
        for (int i=0; i<=rowIndex;i++){
            list.clear();
            for (int j = 0; j <= i; j++) {
                if(j==0||j==i){
                    list.add(1);
                } else {
                    list.add(nums[j-1]+nums[j]);
                }
            }
            for (int i1 = 0; i1 < list.size(); i1++) {
                nums[i1] = list.get(i1);
            }

        }
        return list;
    }

    /**
     * 数学公式
     * @param rowIndex
     * @return
     */
    public List<Integer> getRow2(int rowIndex) {
        List<Integer> ans = new ArrayList<>();

        long pre = 1;
        ans.add(1);
        for (int k = 1; k <= rowIndex; k++) {
            if(k <= (rowIndex+1)/2){
                long cur = pre * (rowIndex - k + 1) / k;
                ans.add((int) cur);
                pre = cur;
            }else{
                ans.add(ans.get(rowIndex-k));
            }

        }
        return ans;
    }


}
