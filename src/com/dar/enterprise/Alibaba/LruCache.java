package com.dar.enterprise.Alibaba;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 146. LRU 缓存 https://leetcode.cn/problems/lru-cache/description/
 * @create :2023-06-19 14:50:00
 */
public class LruCache {

}

class LRUCache {
    private final Map<Integer, Node> map;
    // 头指针
    private final Node head;
    // 尾指针
    private final Node tail;
    // 容量
    private final int size;
    // 节点
    private static class Node {
        private final int key;
        private int value;
        // 前驱节点
        private Node pre;
        // 后继节点
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        this.size = capacity;
        this.map = new HashMap<>(capacity);
        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key) {
        Node node = map.get(key);
        if (node == null) {
            return -1;
        }
        remove(node);
        add(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = map.get(key);
        if (node != null) {
            node.value = value;
            remove(node);
            add(node);
            return;
        }
        if (map.size() == size) {
            remove(map.remove(tail.pre.key));
        }
        Node newNode = new Node(key, value);
        add(newNode);
        map.put(key, newNode);
    }

    private void remove(Node node) {
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    private void add(Node node) {
        node.next = head.next;
        head.next.pre = node;
        head.next = node;
        node.pre = head;
    }
}

