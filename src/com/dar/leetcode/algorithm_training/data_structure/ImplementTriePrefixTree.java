package com.dar.leetcode.algorithm_training.data_structure;

/**
 * @author :wx
 * @description : 208. 实现 Trie (前缀树) https://leetcode.cn/problems/implement-trie-prefix-tree/description/
 * @create :2023-03-20 09:37:00
 */
public class ImplementTriePrefixTree {

    public static void main(String[] args) {

    }


}
class Trie {
    private static class Node{
        Node[] children = new Node[26];
        boolean isEnd;
    }
    private final Node root = new Node();
    public Trie() {

    }

    public void insert(String word) {
        Node node = root;
        for (int i = 0; i <word.length() ; i++) {
            int index = word.charAt(i)-'a';
            if(node.children[index]==null){
                node.children[index] = new Node();
            }
            node = node.children[index];
        }
        node.isEnd = true;
    }

    public boolean search(String word) {
        Node node = searchPrefix(word);
        return node!=null&&node.isEnd;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix)!=null;
    }

    private Node searchPrefix(String prefix){
        Node node = root;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i)-'a';
            if(node.children[index]==null) return null;
            node = node.children[index];
        }
        return node;
    }
}