# codeTop-Alibaba

## LRU 缓存

### 题目描述

[原题链接](https://leetcode.cn/problems/lru-cache/description/)

请你设计并实现一个满足 [LRU (最近最少使用) 缓存](https://baike.baidu.com/item/LRU) 约束的数据结构。

实现 `LRUCache` 类：

- `LRUCache(int capacity)` 以 **正整数** 作为容量 `capacity` 初始化 LRU 缓存
- `int get(int key)` 如果关键字 `key` 存在于缓存中，则返回关键字的值，否则返回 `-1` 。
- `void put(int key, int value)` 如果关键字 `key` 已经存在，则变更其数据值 `value` ；如果不存在，则向缓存中插入该组 `key-value` 。如果插入操作导致关键字数量超过 `capacity` ，则应该 **逐出** 最久未使用的关键字。

函数 `get` 和 `put` 必须以 `O(1)` 的平均时间复杂度运行。

 

**示例：**

```
输入
["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
输出
[null, null, null, 1, null, -1, null, -1, 3, 4]

解释
LRUCache lRUCache = new LRUCache(2);
lRUCache.put(1, 1); // 缓存是 {1=1}
lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
lRUCache.get(1);    // 返回 1
lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
lRUCache.get(2);    // 返回 -1 (未找到)
lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
lRUCache.get(1);    // 返回 -1 (未找到)
lRUCache.get(3);    // 返回 3
lRUCache.get(4);    // 返回 4
```

 

**提示：**

- `1 <= capacity <= 3000`
- `0 <= key <= 10000`
- `0 <= value <= 105`
- 最多调用 `2 * 105` 次 `get` 和 `put`

### 题解

首先需要模拟一个双向链表，便于删除节点与添加节点，使用HashMap来存储节点位置（key为节点的key，value为节点）

查询：从map中获取节点，为空返回-1，不为空将该节点移至头部

插入：判断节点key是否存在，存在则更新并将该节点移至头部，不存在，插入，若容量已满移除尾部节点，再从头部插入节点

```java
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
```

## 无重复字符的最长子串

### 题目描述

[原题链接](https://leetcode.cn/problems/longest-substring-without-repeating-characters/description/)

给定一个字符串 `s` ，请你找出其中不含有重复字符的 **最长子串** 的长度。

 

**示例 1:**

```
输入: s = "abcabcbb"
输出: 3 
解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
```

**示例 2:**

```
输入: s = "bbbbb"
输出: 1
解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
```

**示例 3:**

```
输入: s = "pwwkew"
输出: 3
解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
     请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
```

 

**提示：**

- `0 <= s.length <= 5 * 104`
- `s` 由英文字母、数字、符号和空格组成

### 题解

双指针，右指针用于遍历字符串，当出现重复字符时，移动left来删除重复的字符，字符的记录可以使用Map来完成，key为字符，value为字符下标

```java
    public int lengthOfLongestSubstring(String s) {
        if (s.length() < 2) {
            return s.length();
        }
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < s.length(); right++) {
            if (map.containsKey(s.charAt(right))) {
                // 排除重复字符
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            map.put(s.charAt(right), right);
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
```

排除重复字符不需要while循环，因为Map中不可能有重复的字符（key不会重复）

##  数组中的第K个最大元素

### 题目描述

[原题链接](https://leetcode.cn/problems/kth-largest-element-in-an-array/description/)

给定整数数组 `nums` 和整数 `k`，请返回数组中第 `**k**` 个最大的元素。

请注意，你需要找的是数组排序后的第 `k` 个最大的元素，而不是第 `k` 个不同的元素。

你必须设计并实现时间复杂度为 `O(n)` 的算法解决此问题。

 

**示例 1:**

```
输入: [3,2,1,5,6,4], k = 2
输出: 5
```

**示例 2:**

```
输入: [3,2,3,1,2,4,5,5,6], k = 4
输出: 4
```

 

**提示：**

- `1 <= k <= nums.length <= 105`
- `-104 <= nums[i] <= 104`

### 题解

```java
package com.dar.enterprise.Alibaba;

/**
 * @author :wx
 * @description : 215. 数组中的第K个最大元素 https://leetcode.cn/problems/kth-largest-element-in-an-array/
 * @create :2023-06-25 19:48:00
 */
public class KthLargestElementInAnArray {

    public static void main(String[] args) {

    }

    /**
     * 堆排序
     */
    private static class HeapSort {
        public int findKthLargest(int[] nums, int k) {
            heapSort(nums, k);
            return nums[0];
        }
        int len;

        /**
         * 堆排序，将最大值从堆顶移除，移k-1次，第k次即为数组中的第k个最大元素
         */
        private void heapSort(int[] nums, int k) {
            len = nums.length;
            built(nums);
            for (int i = len - 1; i >= 0 && k > 1; i--, k--) {
                swap(nums, 0, i);
                len -= 1;
                maintain(nums, 0);
            }
        }

        /**
         * 从下往上构建堆
         */
        private void built(int[] nums) {
            // 从第一个非叶子节点开始创建
            for (int i = len / 2 - 1; i >= 0; i--) {
                maintain(nums, i);
            }
        }

        /**
         * 维护堆
         */
        private void maintain(int[] nums, int i) {
            int pos = i;
            // 左节点
            int left = i * 2 + 1;
            // 右节点
            int right = i * 2 + 2;
            // 左节点大，往上移到
            if (left < len && nums[pos] < nums[left]) {
                pos = left;
            }
            // 右节点大，往上移到
            if (right < len && nums[pos] < nums[right]) {
                pos = right;
            }
            // 节点移到可能导致之前的结构发生改变，需要重新维护
            if (pos != i) {
                swap(nums, pos, i);
                maintain(nums, pos);
            }
        }

        /**
         * 交换数组元素
         */
        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }

    /**
     * 快排
     */
    private static class QuickSort {
        public int findKthLargest(int[] nums, int k) {
            quickSort(nums, 0, nums.length - 1, k - 1);
            return nums[k - 1];
        }

        /**
         * 快速排序（分治算法）
         */
        private void quickSort(int[] nums, int low, int high, int k) {
            if (low < high) {
                int pos = partition(nums, low, high);
                // 根据k的位置来选取区域
                if (pos > k) {
                    quickSort(nums, low, pos - 1, k);
                } else {
                    quickSort(nums, pos + 1, high, k);
                }
            }
        }

        /**
         * 分区，以nums[high]为边界，右边均小于nums[high]，左边均大于等于
         */
        private int partition(int[] nums, int low, int high) {
            int center = nums[high];
            // pos前的元素均大于等于nums[high]
            int pos = low;
            for (int i = low; i < high; i++) {
                // 将大于等于nums[high]的元素往前移
                if (nums[i] >= center) {
                    swap(nums, i, pos);
                    pos++;
                }
            }
            // 移动边界
            swap(nums, pos, high);
            return pos;
        }

        /**
         * 交换元素
         */
        private void swap(int[] nums, int a, int b) {
            int temp = nums[a];
            nums[a] = nums[b];
            nums[b] = temp;
        }
    }
}
```

## 反转链表

### 题目描述

[原题链接](https://leetcode.cn/problems/reverse-linked-list/description/)

给你单链表的头节点 `head` ，请你反转链表，并返回反转后的链表。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex1.jpg)

```
输入：head = [1,2,3,4,5]
输出：[5,4,3,2,1]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev1ex2.jpg)

```
输入：head = [1,2]
输出：[2,1]
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**提示：**

- 链表中节点的数目范围是 `[0, 5000]`
- `-5000 <= Node.val <= 5000`

 

### 题解

```java
package com.dar.codetop;

/**
 * @author :wx
 * @description : 206. 反转链表 https://leetcode.cn/problems/reverse-linked-list/description/
 * @create :2023-06-26 22:03:00
 */
public class ReverseLinkedList {
    public static void main(String[] args) {

    }

    /**
     * 反转链表 递归
     */
    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        // 返回尾节点（反转后的头节点）
        ListNode newHead = reverseList(head.next);
        // 反转链表，从后往前
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    /**
     * 反转链表 迭代
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode before = null;
        ListNode after = null;
        // 从前往后依次反转
        while (head != null) {
            // 记录下一个节点位置
            after = head.next;
            // 反转
            head.next = before;
            // 前一个节点（反转后下一个节点的next）
            before = head;
            // 向后移动
            head = after;
        }
        return before;
    }

    private static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
```

