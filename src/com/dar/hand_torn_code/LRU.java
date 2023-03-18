package com.dar.hand_torn_code;

import java.util.HashMap;

/**
 * @author :wx
 * @description :
 * @create :2023-03-16 15:17:00
 */
public class LRU<K, V> {
    private HashMap<K, Node<K, V>> map;
    private int size;
    private Node<K, V> head;
    private Node<K, V> tail;

    private static class Node<K, V> {
        Node<K, V> prev;
        Node<K, V> next;
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }
    public LRU(int size) {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
        head.next = tail;
        tail.prev = head;
        map = new HashMap<>(size);
        this.size = size;
    }
    // 获取值
    public V get(K key) {
        Node<K, V> node = map.get(key);
        if (node == null) return null;
        // 先删除节点
        removeNode(node);
        // 将节点插入头节点后
        insertAfterHead(node);
        return node.value;
    }
    // 添加值
    public void put(K key, V val) {
        Node<K, V> node = map.get(key);
        if (node != null) {
            node.value = val;
            removeNode(node);
        } else {
            if (map.size() == size) { // 删除尾指针的前一个节点
                removeNode(map.remove(tail.prev.key));
            }
            node = new Node<>(key, val);
            insertAfterHead(node);

        }
    }

    // 删除node节点
    public void removeNode(Node<K, V> node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // 在头节点后插入一个节点
    public void insertAfterHead(Node<K, V> node) {
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
        node.prev = head;
    }

}
