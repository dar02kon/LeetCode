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

## K 个一组翻转链表

### 题目描述

[原题链接](https://leetcode.cn/problems/reverse-nodes-in-k-group/description/)

给你链表的头节点 `head` ，每 `k` 个节点一组进行翻转，请你返回修改后的链表。

`k` 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 `k` 的整数倍，那么请将最后剩余的节点保持原有顺序。

你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex1.jpg)

```
输入：head = [1,2,3,4,5], k = 2
输出：[2,1,4,3,5]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/10/03/reverse_ex2.jpg)

```
输入：head = [1,2,3,4,5], k = 3
输出：[3,2,1,4,5]
```

 

**提示：**

- 链表中的节点数目为 `n`
- `1 <= k <= n <= 5000`
- `0 <= Node.val <= 1000`

### 题解

```java
package com.dar.codetop;

/**
 * @author :wx
 * @description : 25. K 个一组翻转链表 https://leetcode.cn/problems/reverse-nodes-in-k-group/description/
 * @create :2023-06-27 21:53:00
 */
public class ReverseNodesInKGroup {

    public static void main(String[] args) {

    }

    public ListNode reverseKGroup(ListNode head, int k) {
        // 新加一个头节点，方便遍历
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 要反转区域的前一个节点
        ListNode pre = newHead;
        // 要反转区域的后一个节点
        ListNode next = head;
        // 反转区域的第一个节点
        ListNode start;
        // 反转区域的最后一个节点
        ListNode end = newHead;
        while (next != null) {
            // 循环k次，找到反转区域的最后一个节点
            for (int i = 0; i < k && end != null; i++) {
                end = end.next;
            }
            if (end == null) {
                break;
            }
            // 记录反转区域的后一个节点
            next = end.next;
            // 断开连接，方便反转
            end.next = null;
            // 记录反转区域的第一个节点
            start = pre.next;
            /**
             * 反转后，start与end交换位置
             */
            pre.next = reverse(start);
            // 连接链表，便于下次循环
            start.next = next;
            // 更新要反转区域的前一个节点
            pre = start;
            // 重新定位end指针
            end = start;
        }
        return newHead.next;
    }

    /**
     * 反转链表
     */
    private ListNode reverse(ListNode start) {
        if (start == null || start.next == null) {
            return start;
        }
        ListNode head = reverse(start.next);
        start.next.next = start;
        start.next = null;
        return head;
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

## 三数之和

### 题目描述

[原题链接](https://leetcode.cn/problems/3sum/description/)

给你一个整数数组 `nums` ，判断是否存在三元组 `[nums[i], nums[j], nums[k]]` 满足 `i != j`、`i != k` 且 `j != k` ，同时还满足 `nums[i] + nums[j] + nums[k] == 0` 。请

你返回所有和为 `0` 且不重复的三元组。

**注意：**答案中不可以包含重复的三元组。

 

 

**示例 1：**

```
输入：nums = [-1,0,1,2,-1,-4]
输出：[[-1,-1,2],[-1,0,1]]
解释：
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0 。
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0 。
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0 。
不同的三元组是 [-1,0,1] 和 [-1,-1,2] 。
注意，输出的顺序和三元组的顺序并不重要。
```

**示例 2：**

```
输入：nums = [0,1,1]
输出：[]
解释：唯一可能的三元组和不为 0 。
```

**示例 3：**

```
输入：nums = [0,0,0]
输出：[[0,0,0]]
解释：唯一可能的三元组和为 0 。
```

 

**提示：**

- `3 <= nums.length <= 3000`
- `-105 <= nums[i] <= 105`

### 题解

```java
package com.dar.codetop;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author :wx
 * @description : 15. 三数之和 https://leetcode.cn/problems/3sum/description/
 * @create :2023-06-28 21:46:00
 */
public class ThreeSum {

    public static void main(String[] args) {

    }

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        // 长度小于3，结果为空
        if (nums.length < 3) {
            return result;
        }
        // 排序
        Arrays.sort(nums);
        // 最小值大于0或者最大值小于0，结果都为空
        if (nums[nums.length - 1] < 0) {
            return result;
        }
        // 选一个元素为基准，双指针找出另外两个元素
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > 0) {
                break;
            }
            // 去重
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            // 双指针寻找元素
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                // 大于0，移动右指针
                if (sum > 0) {
                    right--;
                } else if (sum < 0) { // 小于0，移动左指针
                    left++;
                } else { // 等于0，需要进行去重
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[i]);
                    list.add(nums[left]);
                    list.add(nums[right]);
                    result.add(list);
                    while (left < nums.length - 1 && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    while (right > 0 && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                }
            }
        }
        return result;
    }
}

```

## 排序链表

### 题目描述

[原题链接](https://leetcode.cn/problems/sort-list/description/)

给你链表的头结点 `head` ，请将其按 **升序** 排列并返回 **排序后的链表** 。



 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_1.jpg)

```
输入：head = [4,2,1,3]
输出：[1,2,3,4]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2020/09/14/sort_list_2.jpg)

```
输入：head = [-1,5,3,4,0]
输出：[-1,0,3,4,5]
```

**示例 3：**

```
输入：head = []
输出：[]
```

 

**提示：**

- 链表中节点的数目在范围 `[0, 5 * 104]` 内
- `-105 <= Node.val <= 105`

### 题解

```java
package com.dar.codetop;

/**
 * @author :wx
 * @description : 148. 排序链表 https://leetcode.cn/problems/sort-list/description/
 * @create :2023-06-29 21:50:00
 */
public class SortList {
    public static void main(String[] args) {

    }

    /**
     * 递归
     */
    public ListNode sortList(ListNode head) {
        // 为空或只有一个节点直接返回
        if (head == null || head.next == null) {
            return head;
        }
        // 快慢指针，找到中间节点
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 从中间节点分为两个区域，一左一右
        // 记录右区域起始节点
        ListNode tempSlow = slow.next;
        // 断开链表，划分一左一右两个区域
        slow.next = null;
        // 左区域排序，获取排序后链表头节点
        ListNode left = sortList(head);
        // 右区域排序，获取排序后头节点
        ListNode right = sortList(tempSlow);
        // 合并已经排序的左右两个链表（按顺序合并）
        ListNode tempHead = new ListNode();
        ListNode newHead = tempHead;
        while (left != null && right != null) {
            if (left.val < right.val) {
                tempHead.next = left;
                left = left.next;
            } else {
                tempHead.next = right;
                right = right.next;
            }
            tempHead = tempHead.next;
        }
        // 有一方为空，直接链接另一方
        tempHead.next = left == null ? right : left;
        // 返回合并后的头节点
        return newHead.next;
    }

    /**
     * 迭代
     */
    public ListNode sortList2(ListNode head) {
        int len = getLen(head);
        ListNode newHead = new ListNode();
        newHead.next = head;
        // 分区合并的子链表节点个数，1，2，4直到一整个链表
        for (int i = 1; i < len; i *= 2) {
            // 用于连接合并后的链表（区域排序后的链表）
            ListNode pre = newHead;
            // 链表遍历指针，从头开始
            ListNode cur = newHead.next;
            while (cur != null) {
                /**
                 * 第一个区域
                 */
                // 记录第一个链表的头节点
                ListNode head1 = cur;
                // 遍历找到第二链表
                for (int j = 1; j < i && cur.next != null; j++) {
                    cur = cur.next;
                }
                /**
                 * 第二个区域
                 */
                // 记录第二个链表的头节点
                ListNode head2 = cur.next;
                // 断开，真正形成两个链表
                cur.next = null;
                cur = head2;
                // 遍历找到剩余区域的头节点
                for (int j = 1; j < i && cur != null && cur.next != null; j++) {
                    cur = cur.next;
                }
                /**
                 * 剩余区域
                 */
                // 记录剩余链表的头节点，剩余链表可能为空，需要特殊处理一下
                ListNode next = null;
                if (cur != null) {
                    next = cur.next;
                    // 断开与剩余区域的联系
                    cur.next = null;
                }
                // 合并链表（排序），连接链表（与之前合并的链表进行连接）
                pre.next = merge(head1, head2);
                // pre指针遍历到此次合并的末尾，方便下一次连接
                while (pre.next != null) {
                    pre = pre.next;
                }
                // cur指针指向剩余区域，开始下一个区域的拆分与合并
                cur = next;
            }
        }
        return newHead.next;
    }

    /**
     * 获取链表长度
     */
    private int getLen(ListNode head) {
        int len = 0;
        while (head != null) {
            head = head.next;
            len++;
        }
        return len;
    }

    /**
     * 按顺序合并链表，返回表头
     */
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                cur.next = head1;
                head1 = head1.next;
            } else {
                cur.next = head2;
                head2 = head2.next;
            }
            cur = cur.next;
        }
        cur.next = head1 == null ? head2 : head1;
        return newHead.next;
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

## 排序数组

### 题目描述

[原题链接](https://leetcode.cn/problems/sort-an-array/description/)

给你一个整数数组 `nums`，请你将该数组升序排列。

 



**示例 1：**

```
输入：nums = [5,2,3,1]
输出：[1,2,3,5]
```

**示例 2：**

```
输入：nums = [5,1,1,2,0,0]
输出：[0,0,1,1,2,5]
```

 

**提示：**

- `1 <= nums.length <= 5 * 104`
- `-5 * 104 <= nums[i] <= 5 * 104`

### 题解

```java
package com.dar.codetop;

import java.util.Random;

/**
 * @author :wx
 * @description : 912. 排序数组 https://leetcode.cn/problems/sort-an-array/description/
 * @create :2023-07-01 22:13:00
 */
public class SortAnArray {
    public int[] sortArray(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        return nums;
    }

    private static final Random RANDOM = new Random();

    /**
     * 三指针快排（将与目标元素值相同的元素都移至中间区域，左边区域均小于，右边区域均大于）
     */
    private void quickSort(int[] nums, int left, int right) {
        if (left > right) {
            return;
        }
        // 随机一个目标值
        int random = left + RANDOM.nextInt(right - left + 1);
        // 先移至最左边，防止干扰遍历（非必须）
        swap(nums, random, left);
        // 左指针，负责小于目标值元素的安放
        int leftIndex = left;
        // 右指针，负责大于目标值元素的安放
        int rightIndex = right + 1;
        // 前面交换了，所以从left+1开始遍历
        int i = left + 1;
        while (i < rightIndex) {
            if (nums[i] < nums[left]) { // 值小于目标元素，往左移到
                leftIndex++;
                swap(nums, leftIndex, i);
                i++;
            } else if (nums[i] > nums[left]) { // 值大于目标元素，尝试交换
                // 但不能保证nums[rightIndex]值的大小，
                // 交换后nums[i]可能不符合要求，需要继续判断
                rightIndex--;
                swap(nums, i, rightIndex);
            } else { // 相同则跳过
                i++;
            }
        }
        // 将目标元素移至中间
        swap(nums, leftIndex, left);
        // 继续对左区域进行排序
        quickSort(nums, left, leftIndex - 1);
        // 继续对右区域进行排序
        quickSort(nums, rightIndex, right);
    }

    private void swap(int[] nums, int a, int b) {
        int temp = nums[a];
        nums[a] = nums[b];
        nums[b] = temp;
    }
}

```

## 搜索旋转排序数组

### 题目描述

[原题链接](https://leetcode.cn/problems/search-in-rotated-sorted-array/description/)

整数数组 `nums` 按升序排列，数组中的值 **互不相同** 。

在传递给函数之前，`nums` 在预先未知的某个下标 `k`（`0 <= k < nums.length`）上进行了 **旋转**，使数组变为 `[nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]`（下标 **从 0 开始** 计数）。例如， `[0,1,2,4,5,6,7]` 在下标 `3` 处经旋转后可能变为 `[4,5,6,7,0,1,2]` 。

给你 **旋转后** 的数组 `nums` 和一个整数 `target` ，如果 `nums` 中存在这个目标值 `target` ，则返回它的下标，否则返回 `-1` 。

你必须设计一个时间复杂度为 `O(log n)` 的算法解决此问题。

 

**示例 1：**

```
输入：nums = [4,5,6,7,0,1,2], target = 0
输出：4
```

**示例 2：**

```
输入：nums = [4,5,6,7,0,1,2], target = 3
输出：-1
```

**示例 3：**

```
输入：nums = [1], target = 0
输出：-1
```

 

**提示：**

- `1 <= nums.length <= 5000`
- `-10^4 <= nums[i] <= 10^4`
- `nums` 中的每个值都 **独一无二**
- 题目数据保证 `nums` 在预先未知的某个下标上进行了旋转
- `-10^4 <= target <= 10^4`

### 题解

```java
package com.dar.codetop;

/**
 * @author :wx
 * @description : 33. 搜索旋转排序数组 https://leetcode.cn/problems/search-in-rotated-sorted-array/description/
 * @create :2023-07-02 20:56:00
 */
public class SearchInRotatedSortedArray {
    /**
     * 二分查找
     */
    public int search(int[] nums, int target) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < nums[0]) { // mid在右边有序区域
                // 只有 nums[mid]<=target<= nums[nums.length-1]时才需要移到左指针
                if (nums[mid] <= target && target <= nums[nums.length - 1]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            } else { // mid在左边有序区域
                // 只有 nums[0]<=target<= nums[mid]时才需要移到右指针
                if (nums[0] <= target && target <= nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }
        }
        return -1;
    }
}
```

## 合并 K 个升序链表

### 题目描述

[原题链接](https://leetcode.cn/problems/merge-k-sorted-lists/description/)

给你一个链表数组，每个链表都已经按升序排列。

请你将所有链表合并到一个升序链表中，返回合并后的链表。

 

**示例 1：**

```
输入：lists = [[1,4,5],[1,3,4],[2,6]]
输出：[1,1,2,3,4,4,5,6]
解释：链表数组如下：
[
  1->4->5,
  1->3->4,
  2->6
]
将它们合并到一个有序链表中得到。
1->1->2->3->4->4->5->6
```

**示例 2：**

```
输入：lists = []
输出：[]
```

**示例 3：**

```
输入：lists = [[]]
输出：[]
```

 

**提示：**

- `k == lists.length`
- `0 <= k <= 10^4`
- `0 <= lists[i].length <= 500`
- `-10^4 <= lists[i][j] <= 10^4`
- `lists[i]` 按 **升序** 排列
- `lists[i].length` 的总和不超过 `10^4`

### 题解

```java
package com.dar.codetop;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author :wx
 * @description : 23. 合并 K 个升序链表 https://leetcode.cn/problems/merge-k-sorted-lists/description/
 * @create :2023-07-02 21:25:00
 */
public class MergeKSortedLists {
    /**
     * 分治，从中间划分，两两合并
     */
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        return mergeRange(lists, 0, lists.length - 1);
    }

    /**
     * 分治合并
     */
    private ListNode mergeRange(ListNode[] lists, int left, int right) {
        if (left == right) return lists[left];
        int mid = left + (right - left) / 2;
        // 继续分治合并左区域
        ListNode leftNode = mergeRange(lists, left, mid);
        // 继续分治合并右区域
        ListNode rightNode = mergeRange(lists, mid + 1, right);
        // 合并左右区域
        return merge(leftNode, rightNode);
    }

    /**
     * 合并两个链表
     */
    private ListNode merge(ListNode head1, ListNode head2) {
        ListNode newHead = new ListNode();
        ListNode head = newHead;
        while (head1 != null && head2 != null) {
            if (head1.val < head2.val) {
                head.next = head1;
                head1 = head1.next;
            } else {
                head.next = head2;
                head2 = head2.next;
            }
            head = head.next;
        }
        head.next = head1 == null ? head2 : head1;
        return newHead.next;
    }

    /**
     * 优先队列
     * 将节点一个一个放入队列中，每次取最小的
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        if (lists == null || lists.length == 0) return null;
        // 优先队列，从小到大排序
        PriorityQueue<ListNode> priorityQueue = new PriorityQueue<>(lists.length, Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                priorityQueue.add(node);
            }
        }
        ListNode newHead = new ListNode();
        ListNode cur = newHead;
        while (!priorityQueue.isEmpty()) {
            cur.next = priorityQueue.poll();
            cur = cur.next;
            if (cur.next != null) { // 添加新节点
                priorityQueue.add(cur.next);
            }
        }
        return newHead.next;
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

##  二叉树的层序遍历

### 题目描述

[原题链接](https://leetcode.cn/problems/binary-tree-level-order-traversal/description/)

给你二叉树的根节点 `root` ，返回其节点值的 **层序遍历** 。 （即逐层地，从左到右访问所有节点）。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[9,20],[15,7]]
```

**示例 2：**

```
输入：root = [1]
输出：[[1]]
```

**示例 3：**

```
输入：root = []
输出：[]
```

 

**提示：**

- 树中节点数目在范围 `[0, 2000]` 内
- `-1000 <= Node.val <= 1000`

### 题解

```java
package com.dar.codetop;

import javax.swing.tree.TreeNode;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * @author :wx
 * @description : 102. 二叉树的层序遍历 https://leetcode.cn/problems/binary-tree-level-order-traversal/description/
 * @create :2023-07-03 21:26:00
 */
public class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> record = new ArrayList<>();
            // 遍历一层
            while (size-- > 0) {
                TreeNode node = queue.poll();
                record.add(node.val);
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            list.add(record);
        }
        return list;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

## 二叉树的锯齿形层序遍历

### 题目描述

[原题链接](https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/)

给你二叉树的根节点 `root` ，返回其节点值的 **锯齿形层序遍历** 。（即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/tree1.jpg)

```
输入：root = [3,9,20,null,null,15,7]
输出：[[3],[20,9],[15,7]]
```

**示例 2：**

```
输入：root = [1]
输出：[[1]]
```

**示例 3：**

```
输入：root = []
输出：[]
```

 

**提示：**

- 树中节点数目在范围 `[0, 2000]` 内
- `-100 <= Node.val <= 100`

### 题解

```java
package com.dar.codetop;

import java.util.*;

/**
 * @author :wx
 * @description : 103. 二叉树的锯齿形层序遍历 https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal/description/
 * @create :2023-07-03 21:56:00
 */
public class BinaryTreeZigzagLevelOrderTraversal {
    /**
     * 层次遍历，每一层的节点用双向链表来存储以达到锯齿形
     */
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        if (root == null) return list;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        boolean flag = true;
        while (!queue.isEmpty()) {
            int size = queue.size();
            LinkedList<Integer> deque = new LinkedList<>();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                if (flag) {
                    deque.addLast(node.val);
                } else {
                    deque.addFirst(node.val);
                }
                if (node.left != null) {
                    queue.add(node.left);
                }
                if (node.right != null) {
                    queue.add(node.right);
                }
            }
            flag = !flag;
            list.add(deque);
        }
        return list;
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

## 二叉树的最近公共祖先

### 题目描述

[原题链接](https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/)

给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。

[百度百科](https://baike.baidu.com/item/最近公共祖先/8918834?fr=aladdin)中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（**一个节点也可以是它自己的祖先**）。”

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)

```
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
输出：3
解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2018/12/14/binarytree.png)

```
输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
输出：5
解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
```

**示例 3：**

```
输入：root = [1,2], p = 1, q = 2
输出：1
```

 

**提示：**

- 树中节点数目在范围 `[2, 105]` 内。
- `-109 <= Node.val <= 109`
- 所有 `Node.val` `互不相同` 。
- `p != q`
- `p` 和 `q` 均存在于给定的二叉树中。

### 题解

```java
package com.dar.codetop;


/**
 * @author :wx
 * @description : 236. 二叉树的最近公共祖先 https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/
 * @create :2023-07-04 21:41:00
 */
public class LowestCommonAncestorOfABinaryTree {

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        // root节点等于任意一个节点直接返回
        if (root == null || root.val == p.val || root.val == q.val) {
            return root;
        }
        // 从左子树中寻找目标节点
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        // 从右子树中寻找目标节点
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        // 左子树若没有目标节点，右子树有，则目标节点都在右子树中，直接返回第一个找到的目标节点即可
        // 右子树没有目标节点，左子树有，同上
        // 左右子树都有，返回根节点root
        return left == null ? right : (right == null ? left : root);
    }

    private static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
```

## 反转链表 II

### 题目描述

[原题链接](https://leetcode.cn/problems/reverse-linked-list-ii/description/)

给你单链表的头指针 `head` 和两个整数 `left` 和 `right` ，其中 `left <= right` 。请你反转从位置 `left` 到位置 `right` 的链表节点，返回 **反转后的链表** 。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/02/19/rev2ex2.jpg)

```
输入：head = [1,2,3,4,5], left = 2, right = 4
输出：[1,4,3,2,5]
```

**示例 2：**

```
输入：head = [5], left = 1, right = 1
输出：[5]
```

 

**提示：**

- 链表中节点数目为 `n`
- `1 <= n <= 500`
- `-500 <= Node.val <= 500`
- `1 <= left <= right <= n`

 

### 题解

```java
package com.dar.codetop;

/**
 * @author :wx
 * @description : 92. 反转链表 II https://leetcode.cn/problems/reverse-linked-list-ii/description/
 * @create :2023-07-05 21:20:00
 */
public class ReverseLinkedListII {
    /**
     * 将链表分为三个区域，left前，left-right，right后
     * 定义三个指针，pre用于指向第一个区域末尾，cur用于遍历反转区域，next用于标记遍历的下一个节点
     * 对于反转区域，每遍历一个节点，就将该节点移到到该区域首部，节点指针需要做对应变化
     */
    public ListNode reverseBetween(ListNode head, int left, int right) {
        ListNode newHead = new ListNode();
        newHead.next = head;
        ListNode pre = newHead;
        // 找到第一个区域末尾节点
        for (int i = 0; i < left - 1; i++) {
            pre = pre.next;
        }
        ListNode cur = pre.next;
        ListNode next;
        // 反转区域
        for (int i = 0; i < right - left; i++) {
            // 记录下一个节点
            next = cur.next;
            // 连接下一个节点
            cur.next = next.next;
            // 将当前节点移动到反转区域首部
            next.next = pre.next;
            // 与第一个区域相连
            pre.next = next;
        }
        return newHead.next;
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

