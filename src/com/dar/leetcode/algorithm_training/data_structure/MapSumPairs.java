package com.dar.leetcode.algorithm_training.data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 677. 键值映射 https://leetcode.cn/problems/map-sum-pairs/description/
 * @create :2023-03-20 10:04:00
 */
public class MapSumPairs {

    public static void main(String[] args) {

    }

}
class MapSum {

    private static class Tire{
        int val;
        Map<Character,Tire> children = new HashMap<>();
        boolean isEnd;
    }
    private final Tire root = new Tire();
    public MapSum() {

    }

    public void insert(String key, int val) {
        Tire node = root;
        for (int i = 0; i <key.length() ; i++) {
            char index = key.charAt(i);
            if(!node.children.containsKey(index)){
                Tire next = new Tire();
                node.children.put(index,next);
            }
            node = node.children.get(index);
        }
        node.val = val;
        node.isEnd = true;
    }

    public int sum(String prefix) {
        Tire node = root;
        // 找到前缀对应的最后一个字符
        for (int i = 0; i < prefix.length(); i++) {
            char index = prefix.charAt(i);
            if(!node.children.containsKey(index)){
                return 0;
            }
            node = node.children.get(index);
        }
        return dfs(node);
    }

    // 根据前缀最后一个字符的位置向后深搜（后面的字符串肯定包含该前缀）
    private int dfs(Tire node) {
        int sum = 0;
        if(node.isEnd){
            sum+=node.val;
        }
        for (Tire tire : node.children.values()) {
            sum+=dfs(tire);
        }
        return sum;
    }
}
