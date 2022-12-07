## 剑指 Offer 37. 序列化二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/xu-lie-hua-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/SerializeTheBinaryTree.java)

请实现两个函数，分别用来序列化和反序列化二叉树。

你需要设计一个算法来实现二叉树的序列化与反序列化。这里不限定你的序列 / 反序列化算法执行逻辑，你只需要保证一个二叉树可以被序列化为一个字符串并且将这个字符串反序列化为原始的树结构。

**提示：**输入输出格式与 LeetCode 目前使用的方式一致，详情请参阅 [LeetCode 序列化二叉树的格式](https://support.leetcode-cn.com/hc/kb/article/1567641/)。你并非必须采取这种方式，你也可以采用其他的方法解决这个问题。

 

**示例：**

![img](https://assets.leetcode.com/uploads/2020/09/15/serdeser.jpg)

```
输入：root = [1,2,3,null,null,4,5]
输出：[1,2,3,null,null,4,5]
```

 

### 题解

#### 层次遍历

*获取序列*

层次遍历二叉树，使用`StringBuilder`来保存结果，节点值之间用空格隔开，null 用 ! 来代替。使用队列来存储节点辅助遍历。

若从队列取出的节点为空则添加 !，不为空添加其左右节点至队列

最终返回`StringBuilder`对应的字符串

```java
    public String serialize(TreeNode root) {
        if (root == null) {//树为空返回null
            return null;
        }
        StringBuilder result = new StringBuilder();
        Queue<TreeNode> queue = new LinkedList<>();//辅助队列
        queue.add(root);
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(node!=null){
                result.append(node.val).append(" ");//保存节点值
                //添加其左右节点
                queue.add(node.left);
                queue.add(node.right);
            } else {//添加空标志
                result.append("! ");
            }
        }
        return result.toString();
    }

```

**复杂度分析：**

时间复杂度：O(N)

空间复杂度：O(N) 

*解析序列*

使用空格将字符串转成字符数组，添加数组第一个元素至队列。遍历数组，从队列中取出一个元素，不为空则添加其左右节点，为空则跳过，直接移动数组指针。

```java
    public TreeNode deserialize(String data) {
        if (data == null) {//序列为空返回null
            return null;
        }

        String[] s = data.split(" ");//分割字符串
        TreeNode head = new TreeNode(Integer.parseInt(s[0]));//头节点
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(head);
        int i=1;
        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            if(!s[i].equals("!")) {//左节点不为空
                node.left = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.left);
            }
            i++;
            if(!s[i].equals("!")) {//右节点不为空
                node.right = new TreeNode(Integer.parseInt(s[i]));
                queue.add(node.right);
            }
            i++;
        }
        return head;
    }
```

**复杂度分析：**

时间复杂度：O(N)

空间复杂度：O(N) 

## 剑指 Offer 38. 字符串的排列

### 题目描述

[原题链接](https://leetcode.cn/problems/zi-fu-chuan-de-pai-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ArrangementOfStrings.java)

输入一个字符串，打印出该字符串中字符的所有排列。

 

你可以以任意顺序返回这个字符串数组，但里面不能有重复元素。

 

**示例:**

```
输入：s = "abc"
输出：["abc","acb","bac","bca","cab","cba"]
```

 

**限制：**

```
1 <= s 的长度 <= 8
```

### 题解

#### 回溯

递归回溯找出所有的可能，需要数组标记数组来记录哪些位置访问过。为了去重，可以将字符串转成数组对其进行排序，在递归前先判断这个元素是否之前已经出现过。避免出现`aab`，以第一个`a`为开头后又以第二个`a`为开头

```java
    boolean[] visited;//标记数组
    StringBuilder record = new StringBuilder();
    List<String> list = new ArrayList<>();
    public String[] permutation(String s) {
        visited = new boolean[s.length()];
        char[] array = s.toCharArray();
        Arrays.sort(array);//排序
        record(array);
        String[] result = new String[list.size()];
        return list.toArray(result);
    }
    public void record(char[] s){
        if(record.length()==s.length){
            list.add(new String(record));
        }
        for (int i = 0; i < s.length; i++) {
            if(visited[i]||(i>0&&!visited[i-1]&&s[i-1]==s[i])){//去重
                continue;
            }
            record.append(s[i]);
            visited[i] = true;
            record(s);
            //恢复初始状态
            record.deleteCharAt(record.length()-1);
            visited[i] = false;
        }
    }
```

**复杂度分析：**

时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。这些字符的全部排列有 O(n!) 个，每个排列平均需要 O(n) 的时间来生成

空间复杂度：O(n)。我们需要 O(n) 的栈空间进行回溯，标记数组根据题目要求长度不会超过8，返回值不计入空间复杂度

#### 下一个排列

[下一个排列](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/medium/NextPermutation.java)

先将字符串转成字符数组，然后进行从小到大的排序，排序后的结果便是一个最小排列，以这个最小排列开始去寻找下一个排列，直到找完

```java
    List<String> list2 = new ArrayList<>();
    public String[] permutation2(String s) {
        char[] array = s.toCharArray();
        Arrays.sort(array);//排序
        do {
            list2.add(new String(array));
        } while (next(array));
        String[] result = new String[list2.size()];
        return list2.toArray(result);
    }

    public boolean next(char[] array) {//下一个排列，返回true表示存在
        int left = array.length - 2;
        while (left >= 0 && array[left] >= array[left + 1]) {//寻找较小的值
            left--;
        }
        if (left < 0) {//越界判断
            return false;
        }
        int right = array.length - 1;
        while (right >= 0 && array[right] <= array[left]) {//在较小值的基础上寻找一个较大值
            right--;
        }
        if (right >= array.length) {//越界判断
            return false;
        }

        //交换较小值与较大值
        char target = array[left];
        array[left] = array[right];
        array[right] = target;
        left = left + 1;
        right = array.length - 1;
        while (left < right) {//对原较小值位置后面的元素进行排序
            //排序只需要左右交换就可以了
            target = array[left];
            array[left] = array[right];
            array[right] = target;
            left++;
            right--;
        }
        return true;
    }
```

**复杂度分析：**

时间复杂度：O(n×n!)，其中 n 为给定字符串的长度。我们需要 O(*nlogn*) 的时间得到第一个排列，next 函数的时间复杂度为 O(n)，我们至多执行该函数 O(n!)次，因此总时间复杂度为 O(n×n!)

空间复杂度：O(1)，返回值不计入空间复杂度

