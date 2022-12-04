package com.dar.leetcode.the_sword_refers_to_offer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :wx
 * @description : 剑指 Offer 35. 复杂链表的复制 https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/?favorite=xb9nqhhg
 * @create :2022-12-04 10:49:00
 */
public class ReplicationOfComplexLinkedLists {

    public static void main(String[] args) {

    }

    Map<Node, Node> map = new HashMap<>();

    /**
     * 哈希表 + 两次遍历
     *
     * @param head
     * @return
     */
    public Node copyRandomList(Node head) {
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));
            p = p.next;
        }
        p = head;
        while (p != null) {
            Node node = map.get(p);
            node.next = map.get(p.next);
            node.random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);
    }

    /**
     * 哈希表 + 递归
     *
     * @param head
     * @return
     */
    public Node copyRandomList2(Node head) {
        if (head == null) {
            return null;
        }

        if (!map.containsKey(head)) {
            Node node = new Node(head.val);
            map.put(head, node);
            node.next = copyRandomList2(head.next);
            node.random = copyRandomList2(head.random);
        }
        return map.get(head);
    }

    /**
     * 迭代 + 节点拆分
     * @param head
     * @return
     */
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }

        for (Node node = head; node != null; node = node.next.next) {
            Node newNode = node.next;
            newNode.random = node.random == null ? null : node.random.next;
        }

        Node newHead = head.next;
        for (Node node = head; node != null; node = node.next) {
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return newHead;
    }
}

class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
