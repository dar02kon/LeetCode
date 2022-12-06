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