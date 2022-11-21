# 剑指offer（二）

## 剑指 Offer 18. 删除链表的节点

### 题目描述

[原题链接](https://leetcode.cn/problems/shan-chu-lian-biao-de-jie-dian-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/DeleteTheNodesOfTheLinkedList.java)

给定单向链表的头指针和一个要删除的节点的值，定义一个函数删除该节点。

返回删除后的链表的头节点。

**注意：**此题对比原题有改动

**示例 1:**

```
输入: head = [4,5,1,9], val = 5
输出: [4,1,9]
解释: 给定你链表中值为 5 的第二个节点，那么在调用了你的函数之后，该链表应变为 4 -> 1 -> 9.
```

**示例 2:**

```
输入: head = [4,5,1,9], val = 1
输出: [4,5,9]
解释: 给定你链表中值为 1 的第三个节点，那么在调用了你的函数之后，该链表应变为 4 -> 5 -> 9.
```

 

**说明：**

- 题目保证链表中节点的值互不相同
- 若使用 C 或 C++ 语言，你不需要 `free` 或 `delete` 被删除的节点

### 题解

#### 遍历删除

删除需要对头节点先做处理，如果要删除头节点head，则直接返回head.next，也可以在头节点前增加一个空节点来辅助判断

```java
   public ListNode deleteNode(ListNode head, int val) {
        ListNode root =new ListNode();//在头节点前增加一个空节点
        root.next = head;
        ListNode p = root;
        while (head!=null){
            if(head.val==val){
                root.next = head.next;
                head.next=null;
                break;
            }
            head = head.next;
            root = root.next;
        }
        return p.next;
    }
```

```java
    public ListNode deleteNode2(ListNode head, int val) {
       if(head.val==val){//先判断头节点
           return head.next;
       }
       ListNode left = head;
       ListNode right = head.next;
       while (right!=null){
           if(right.val==val){
               left.next = right.next;
               right.next = null;
               break;
           }
           right = right.next;
           left = left.next;
       }
       return head;
    }
```

**复杂度分析：**

时间复杂度 O(N) ： N 为链表长度，删除操作平均需循环 N/2 次，最差 N 次

空间复杂度 O(1) ： 占用常数大小额外空间

## 剑指 Offer 21. 调整数组顺序使奇数位于偶数前面

### 题目描述

[原题链接](https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/AdjustTheArrayOrderSoThatTheOddNumbersPrecedeTheEvenOnes.java)

输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。

 

**示例：**

```
输入：nums = [1,2,3,4]
输出：[1,3,2,4] 
注：[3,1,2,4] 也是正确的答案之一。
```

 

**提示：**

1. `0 <= nums.length <= 50000`
2. `0 <= nums[i] <= 10000`

### 题解

#### 双指针

设置两个指针，一左一右，左指针负责找到偶数，右指针负责找到奇数，当左指针在左边找到偶数，同时右指针在右边找到奇数时两者交换，左右指针继续寻找直到左指针与右指针指向同一位置。

这是在原数组的基础上去交换位置，没有开辟新的数组

```java
    public int[] exchange(int[] nums) {
        int left = 0;
        int right = nums.length-1;
        while (left<right){//循环条件
            while (left<right&&nums[left]%2==1){//寻找偶数
                left++;
            }
            while (left<right&&nums[right]%2==0){//寻找奇数
                right--;
            }
            if(left<right){//交换
                int target = nums[left];
                nums[left] = nums[right];
                nums[right] = target;
                left++;
                right--;
            }
        }
        return nums;
    }
```

**复杂度分析：**

时间复杂度：O(n)。原数组中每个元素只遍历一次。

空间复杂度：O(1)。原地调整，只消耗常数空间。

也可以开辟新的数组来保存结果，同样需要两个指针，遍历原数组，如果为奇数则放到新数组左边，如果为偶数则放到新数组右边

```java
    public int[] exchange2(int[] nums) {
        int[] result = new int[nums.length];//保存结果的数组
        int left = 0;
        int right = nums.length-1;
        for (int num : nums) {//遍历数组
            if(num%2==0){//偶数
                result[right--]=num;//保存到右边
            } else {//奇数
                result[left++] =num;//保存到左边
            }
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为数组 nums 的长度。只需遍历 nums 一次。

空间复杂度：O(1)。结果不计入空间复杂度。
