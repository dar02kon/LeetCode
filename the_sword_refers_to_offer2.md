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

## 剑指 Offer 19. 正则表达式匹配

### 题目描述

[原题链接](https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/RegularExpressionMatching.java)

请实现一个函数用来匹配包含`'. '`和`'*'`的正则表达式。模式中的字符`'.'`表示任意一个字符，而`'*'`表示它前面的字符可以出现任意次（含0次）。在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串`"aaa"`与模式`"a.a"`和`"ab*ac*a"`匹配，但与`"aa.a"`和`"ab*a"`均不匹配。

**示例 1:**

```
输入:
s = "aa"
p = "a"
输出: false
解释: "a" 无法匹配 "aa" 整个字符串。
```

**示例 2:**

```
输入:
s = "aa"
p = "a*"
输出: true
解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
```

**示例 3:**

```
输入:
s = "ab"
p = ".*"
输出: true
解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
```

**示例 4:**

```
输入:
s = "aab"
p = "c*a*b"
输出: true
解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
```

**示例 5:**

```
输入:
s = "mississippi"
p = "mis*is*p*."
输出: false
```

- `s` 可能为空，且只包含从 `a-z` 的小写字母。
- `p` 可能为空，且只包含从 `a-z` 的小写字母以及字符 `.` 和 `*`，无连续的 `'*'`。

### 题解

#### 动态规划

设字符串为s，正则串为p。我们要判断字符串s与p是否匹配需要根据前面的已知条件再加上现在所知道的信息进行判断

例如，我们要判断`aab`与`c*a*b`是否匹配，需要先知道`aa`与`c*a*`是否匹配，同样判断`aa`与`c*a*`是否匹配需要先判断`aa`与`c*`或者`a`与`c*a*`是否匹配（`*`比较特殊）

所以我们可以采用动态规划来处理，设置数组`dp[i][j]`表示s串的前i个字符与p串的前j个字符是否匹配

**匹配前**

在匹配前可以确定`dp[0][0]`肯定为`true`，空串和空串是匹配的，`dp[0][j]`可能为`true`，字符串为空，但匹配串可能为`字符+*`依然可以匹配，在i>0的情况下，`dp[i][0]`肯定为`false`，正则串为空不可能匹配一个非空字符串

**匹配过程中**

在匹配过程中主要分为两种情况

* `p.charAt(j)=='*'`：`'*'`表示它前面的字符可以出现任意次（含0次），如果枚举起来会很复杂，我们需要将`字符+*`看成是一个组合，一种情况是放弃这个组合即`*`前面的字符可以出现0次，这时`dp[i][j] = dp[i][j - 2]`；另外一种情况是进行匹配，需要先判断`p.charAt(j-1)`（`*`前一个字符）与`s.charAt(i)`是否匹配，如果匹配则`dp[i][j] = dp[i][j] || dp[i - 1][j]`，这里出现`dp[i][j]`是为了防止覆盖第一种情况，两者满足一种都是可以匹配的
* `p.charAt(j)！='*'`：若正则串当前获取的字符不为`*`，只需要比较正则串字符`p.charAt(j)`与对于的字符串字符`s.charAt(i)`是否匹配，如果匹配，则`dp[i][j] = dp[i - 1][j - 1]`

需要注意字符`'.'`可以表示任意一个字符，不表示空串

```java
    public boolean isMatch(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];
        dp[0][0] = true;//空串与空串是匹配的
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 1; j <= p.length(); j++) {//j=0时不需要进行判断
                if (p.charAt(j - 1) != '*') {//不为 *
                    if (match(s, p, i, j))//判断对应字符是否匹配
                        dp[i][j] = dp[i - 1][j - 1];
                } else {//为 *
                    dp[i][j] = dp[i][j - 2];//第一种情况
                    if (match(s, p, i, j - 1)) {//第二种情况
                        dp[i][j] = dp[i][j] || dp[i - 1][j];//只要有一种情况为true，结果就为true
                    }
                }
            }
        }
        return dp[s.length()][p.length()];
    }

    public boolean match(String s, String p, int i, int j) {//判断对应字符是否匹配
        if (i == 0) {//为0则直接返回
            return false;
        }
        if (p.charAt(j - 1) == '.') {//. 匹配任意一个字符
            return true;
        }
        return s.charAt(i - 1) == p.charAt(j - 1);
    }
```

**复杂度分析：**

时间复杂度：O(mn)，其中 m 和 n 分别是字符串 s 和 p 的长度。我们需要计算出所有的状态，并且每个状态在进行转移时的时间复杂度为 O(1)。

空间复杂度：O(mn)，即为存储所有状态使用的空间。

## 剑指 Offer 20. 表示数值的字符串

### 题目描述

[原题链接](https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/AStringRepresentingANumericValue.java)

请实现一个函数用来判断字符串是否表示**数值**（包括整数和小数）。

**数值**（按顺序）可以分成以下几个部分：

1. 若干空格
2. 一个 **小数** 或者 **整数**
3. （可选）一个 `'e'` 或 `'E'` ，后面跟着一个 **整数**
4. 若干空格

**小数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 下述格式之一：
   1. 至少一位数字，后面跟着一个点 `'.'`
   2. 至少一位数字，后面跟着一个点 `'.'` ，后面再跟着至少一位数字
   3. 一个点 `'.'` ，后面跟着至少一位数字

**整数**（按顺序）可以分成以下几个部分：

1. （可选）一个符号字符（`'+'` 或 `'-'`）
2. 至少一位数字

部分**数值**列举如下：

- `["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]`

部分**非数值**列举如下：

- `["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]`

 

**示例 1：**

```
输入：s = "0"
输出：true
```

**示例 2：**

```
输入：s = "e"
输出：false
```

**示例 3：**

```
输入：s = "."
输出：false
```

**示例 4：**

```
输入：s = "    .1  "
输出：true
```

 

**提示：**

- `1 <= s.length <= 20`
- `s` 仅含英文字母（大写和小写），数字（`0-9`），加号 `'+'` ，减号 `'-'` ，空格 `' '` 或者点 `'.'` 。

### 题解

#### 普通解法

根据条件，不断的`if else`，然后根据测试结果再去修改相应条件就能写出来

```java
    public boolean isNumber(String s) {
        boolean pointCount = false;//判断 .出现,出现改为true
        boolean flag = false;//判断E/e是否出现
        boolean target = false;//判断E/e的出现是否合法，E/e只能出现在小数点或者数字后面
        s = s.trim();//去掉前后空格
        for (int i = 0; i < s.length(); i++) {//遍历
            char c = s.charAt(i);
            if (c == ' ') {//中间出现空格，则不能表示数值
                return false;
            }
            if (c == '+' || c == '-') {//正负符号
                //判断正负符号出现的位置是否合法，在第一个有效位置或者在E/e前
                if (!(i == 0 || (s.charAt(i - 1) == 'E' || s.charAt(i - 1) == 'e'))) {
                    return false;
                }
                //判断正负符号的使用是否合法，其后必须有数字
                if (!(i < s.length() - 1 && (s.charAt(i + 1) >= '0' || s.charAt(i + 1) <= '9'))) {
                    return false;
                }
            } else {
                if (c < '0' || c > '9') {//非数字符号
                    if (flag) {//已出现E/e，其后只能跟着一个整数
                        return false;
                    }
                    if (c == '.') {//小数点
                        if (pointCount) {//小数点只能出现一次
                            return false;
                        }
                        //判断小数点的使用是否合法，小数点的前面或者后面至少有一个数字
                        if (!((i > 0 && (s.charAt(i - 1) >= '0' && s.charAt(i - 1) <= '9')) || (i < s.length() - 1 && (s.charAt(i + 1) >= '0' && s.charAt(i + 1) <= '9')))) {
                            return false;
                        }
                        pointCount = true;//标记小数点已出现
                        target = true;//允许E/e出现
                    } else if (c == 'e' || c == 'E') {//E/e
                        if (!target || i == s.length() - 1) {//出现条件不符合，即前面即没有数字也没有小数点，或者出现在最后一个位置
                            return false;
                        }
                        flag = true;//标记E/e已出现
                    } else {
                        return false;//出现了奇怪的字符
                    }
                } else {
                    target = true;//允许E/e出现
                }
            }
        }
        return 0 != s.length();
    }
```

**复杂度分析：**

时间复杂度：O(n)。其中 n 为字符串的长度。我们需要遍历字符串的每个字符。

空间复杂度：O(1)。



#### 确定有限状态自动机

确定有限状态自动机（以下简称「自动机」）是一类计算模型。它包含一系列状态，这些状态中：

有一种特殊的状态，被称作「初始状态」。
还有一系列状态被称为「接受状态」，它们组成了一个特殊的集合。其中，一个状态可能既是「初始状态」，也是「接受状态」。
起初，这个自动机处于「初始状态」。随后，它顺序地读取字符串中的每一个字符，并根据当前状态和读入的字符，按照某个事先约定好的「转移规则」，从当前状态转移到下一个状态；当状态转移完成后，它就读取下一个字符。当字符串全部读取完毕后，如果自动机处于某个「接受状态」，则判定该字符串「被接受」；否则，判定该字符串「被拒绝」。

如果输入的过程中某一步转移失败了，即不存在对应的「转移规则」，此时计算将提前中止。在这种情况下我们也判定该字符串「被拒绝」。

一个自动机，总能够回答某种形式的「对于给定的输入字符串 S，判断其是否满足条件 P」的问题。在本题中，条件 P 即为「构成合法的表示数值的字符串」。

自动机驱动的编程，可以被看做一种暴力枚举方法的延伸：它穷尽了在任何一种情况下，对应任何的输入，需要做的事情。

自动机在计算机科学领域有着广泛的应用。在算法领域，它与大名鼎鼎的字符串查找算法「KMP」算法有着密切的关联；在工程领域，它是实现「正则表达式」的基础。

**实现思路：**

*确定状态*

根据题意可分为以下十种

* 0	起始的空格
* 1    符号位
* 2    整数部分
* 3    左侧有整数的小数点
* 4    左侧无整数的小数点
* 5    小数部分
* 6    E/e
* 7    指数部分的符号位
* 8    指数部分的整数部分
* 9    末尾的空格

*确定接受字符*

根据题意可以有六种

* n	0到9的数字
* p    小数点
* e    E/e
* b    空格
* s     正负号
* u     其它字符

*确定状态转移规则*

采用官方题解的图

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-23_18-59-26.png)

根据上面的思路写出代码即可，初始状态设为0，即起始空格，可以用Map类型的一维数组来确定状态转移，2，3，5，8，9是可以数值的状态

```java
    public boolean isNumber2(String s) {
        Map[] transfer = {
                new HashMap<Character, Integer>() {{//状态0接受字符
                    put('b',0);
                    put('n',2);
                    put('p',4);
                    put('s',1);
                }},
                new HashMap<Character, Integer>(){{//状态1接受字符
                    put('p',4);
                    put('n',2);
                }},
                new HashMap<Character, Integer>(){{//状态2接受字符
                    put('n',2);
                    put('e',6);
                    put('p',3);
                    put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态3接受字符
                   put('n',5);
                   put('e',6);
                   put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态4接受字符
                    put('n',5);
                }},
                new HashMap<Character, Integer>(){{//状态5接受字符
                   put('n',5);
                   put('e',6);
                   put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态6接受字符
                   put('n',8);
                   put('s',7);
                }},
                new HashMap<Character, Integer>(){{//状态7接受字符
                    put('n',8);
                }},
                new HashMap<Character, Integer>(){{//状态8接受字符
                    put('n',8);
                    put('b',9);
                }},
                new HashMap<Character, Integer>(){{//状态9接受字符
                   put('b',9);
                }}
        };
        int state = 0;//初始状态
        char t;
        for (char c : s.toCharArray()) {
            if(c>='0'&&c<='9'){
                t = 'n';//接受数字
            } else if(c=='.'){
                t = 'p';//接受小数点
            } else if(c=='E'||c=='e'){
                t = 'e';//接受E/e
            } else if(c==' '){
                t= 'b';//接受空格
            } else if(c=='+'||c=='-'){
                t = 's';//接受正负号
            } else {
                return false;
            }
            if(!transfer[state].containsKey(t)){//若此状态不能接受该字符，则直接返回false
                return false;
            }
            state = (int)transfer[state].get(t);//状态转移
        }
        return state==2||state==3||state==5||state==8||state==9;//2，3，5，8，9是可以数值的状态
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为字符串的长度。我们需要遍历字符串的每个字符，其中状态转移所需的时间复杂度为 O(1)。

空间复杂度：O(1)。只需要创建固定大小的状态转移表。



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

## 剑指 Offer 22. 链表中倒数第k个节点

### 题目描述

[原题链接](https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheKTHLastNodeInTheList.java)

输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。

例如，一个链表有 `6` 个节点，从头节点开始，它们的值依次是 `1、2、3、4、5、6`。这个链表的倒数第 `3` 个节点是值为 `4` 的节点。

 

**示例：**

```
给定一个链表: 1->2->3->4->5, 和 k = 2.

返回链表 4->5.
```

### 题解

#### 快慢指针

可以定义两个指针，快指针先跑K个单位，然后快慢指针一起跑，这样快指针始终领先满指针K个单位，当快指针到达终点时，满指针就到达了倒数第K个单位上

```java
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode left = head;
        ListNode right = head;
        while (k>0){//快指针先跑
            right = right.next;
            k--;
        }
        while (right!=null){//快慢指针一起跑
            left = left.next;
            right = right.next;
        }
        return left;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 为链表的长度。我们使用快慢指针，只需要一次遍历即可，复杂度为 O(n)。

空间复杂度：O(1)。

## 剑指 Offer 24. 反转链表

### 题目描述

[原题链接](https://leetcode.cn/problems/fan-zhuan-lian-biao-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ReverseLinkedList.java)

定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。

 

**示例:**

```
输入: 1->2->3->4->5->NULL
输出: 5->4->3->2->1->NULL
```

 

**限制：**

```
0 <= 节点个数 <= 5000
```

 

### 题解

#### 迭代遍历

从左往右依次反转链表，需要设置两个指针来辅助进行反转（需要记录前一个节点与后一个节点的位置），并且之前的头节点的next指针需要指向null（否则会出现环）

```java
    public ListNode reverseList(ListNode head) {
        ListNode left = null;//反转后之前头节点的next指针需要指向null
        ListNode right;
        while (head != null) {
            right = head.next;//先记录下一个位置
            head.next = left;//反转
            //指针后移
            left = head;
            head = right;
        }
        return left;//返回反转后的头节点
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是链表的长度。需要遍历链表一次

空间复杂度：O(1)

#### 递归

使用递归相当于从后往前反转链表，先找到尾节点即反转后的头节点，尾节点返回的过程中依次进行反转

```java
    public ListNode reverseList2(ListNode head) {
        if (head == null || head.next == null) {
            return head;//返回新的头节点
        }
        ListNode newHead = reverseList2(head.next);
        //反转
        head.next.next = head;
        head.next = null;//主要是为了使之前的头节点的next指针指向null，其它位置都会被覆盖
        return newHead;//返回头节点
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是链表的长度。需要对链表的每个节点进行反转操作。

空间复杂度：O(n)，其中 n 是链表的长度。空间复杂度主要取决于递归调用的栈空间，最多为 n 层

## 剑指 Offer 25. 合并两个排序的链表

### 题目描述

[原题链接](https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/MergeTwoSortedLinkedLists.java)

输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。

**示例1：**

```
输入：1->2->4, 1->3->4
输出：1->1->2->3->4->4
```

**限制：**

```
0 <= 链表长度 <= 1000
```

### 题解

#### 迭代

先设置一个空的头节点来记录头节点的位置，再设置两个指针分别指向两个链表的头节点，比较两指针所对应的节点值，先添加较小的节点，并将对应的指针后移。当两指针有一方为空时，直接将另一方连接到新链表的尾部

```java
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode head = new ListNode();
        ListNode p = head;
        while (l1!=null&&l2!=null){
            if(l1.val<l2.val){//先添加比较小的节点
                p.next = l1;
                l1 = l1.next;
            } else {
                p.next = l2;
                l2 = l2.next;
            }
            p = p.next;
        }
        p.next = l1==null?l2:l1;//不为空的直接与尾部连接
        return head.next;//返回头节点
    }
```

**复杂度分析：**

时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次循环迭代中，l1 和 l2 只有一个元素会被放进合并链表中， 因此 while 循环的次数不会超过两个链表的长度之和。所有其他操作的时间复杂度都是常数级别的，因此总的时间复杂度为 O(n+m)。

空间复杂度：O(1)。我们只需要常数的空间存放若干变量



#### 递归

上面的思路也可以用递归来实现，都是寻找两链表中最小的节点加入到合并链表中然后继续去寻找剩下节点中的最小节点

如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，只需要返回非空链表。否则，要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。如果两个链表有一个为空，递归结束。

```java
    public ListNode mergeTwoLists2(ListNode l1, ListNode l2) {
       if(l1==null){//l1为空，返回l2
           return l2;
       } else if(l2==null){//l2为空返回l1
           return l1;
       } else if(l1.val<l2.val){//都不为空则先添加比较小的节点
           l1.next = mergeTwoLists2(l1.next,l2);//l1头节点后移，继续寻找最小节点
           return l1;
       } else {
           l2.next = mergeTwoLists2(l1,l2.next);//l2头节点后移，继续寻找最小节点
           return l2;
       }
    }
```

**复杂度分析：**

时间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。

空间复杂度：O(n+m)，其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。

## 剑指 Offer 26. 树的子结构

### 题目描述

[原题链接](https://leetcode.cn/problems/shu-de-zi-jie-gou-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheSubstructureOfATree.java)

输入两棵二叉树A和B，判断B是不是A的子结构。(约定空树不是任意一个树的子结构)

B是A的子结构， 即 A中有出现和B相同的结构和节点值。

**示例 1：**

```
输入：A = [1,2,3], B = [3,1]
输出：false
```

**示例 2：**

```
输入：A = [3,4,5,1,2], B = [4,1]
输出：true
```

**限制：**

```
0 <= 节点个数 <= 10000
```

### 题解

#### 递归

可以先序遍历A树的每一个节点`An`，再将以`An`为根节点的子树与B进行比较

```java
    public boolean isSubStructure(TreeNode A, TreeNode B) {
        //先序遍历A的每一个节点，并将以此节点为根节点的子树与B进行比较
        return (A != null && B != null) && (traversal(A, B) || isSubStructure(A.left, B) || isSubStructure(A.right, B));
    }

    public boolean traversal(TreeNode A, TreeNode B) {//依次比较每个节点
        if (B == null) {//B为空说明B是A的子结构（B已经比较完了）
            return true;
        }
        if (A == null || A.val != B.val) {//A为空或节点值不相等
            return false;
        }
        return traversal(A.left, B.left) && traversal(A.right, B.right);//递归比较左子树与右子树是否相同
    }
```

**复杂度分析：**

时间复杂度： O(MN)，其中 M,N 分别为树 A 和 树 B 的节点数量；先序遍历树 A 占用 O(M) ，每次调用 traversal(A, B) 判断占用 O(N)

空间复杂度：O(M)： 当树 A 和树 B 都退化为链表时，递归调用深度最大。当 M<=N时，遍历树 A 与递归判断的总递归深度为 M ；当 M>N 时，最差情况为遍历至树 A 叶子节点，此时总递归深度为 M



## 剑指 Offer 27. 二叉树的镜像

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-shu-de-jing-xiang-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheMirrorImageOfABinaryTree.java)

请完成一个函数，输入一个二叉树，该函数输出它的镜像。

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-11-27_16-31-41.png)

**示例 1：**

```
输入：root = [4,2,7,1,3,6,9]
输出：[4,7,2,9,6,3,1]
```

 

**限制：**

```
0 <= 节点个数 <= 1000
```

### 题解

#### 递归

我们从根节点开始，递归地对树进行遍历，并从叶子节点先开始翻转得到镜像。如果当前遍历到的节点 root 的左右两棵子树都已经翻转得到镜像，那么我们只需要交换两棵子树的位置，即可得到以 root 为根节点的整棵子树的镜像。

递归交换是一个从下往上的过程

```java
    public TreeNode mirrorTree2(TreeNode root) {
        if (root == null) {
            return null;
        }
        TreeNode left = mirrorTree2(root.left);//去反转左子树
        TreeNode right = mirrorTree2(root.right);//去反转右子树
        //交换反转后的左右子树，得到以 root 为根节点的整棵子树的镜像
        root.left = right;
        root.right = left;
        return root;
    }
```

**复杂度分析：**

时间复杂度：O(N)，其中 N 为二叉树节点的数目。我们会遍历二叉树中的每一个节点，对每个节点而言，我们在常数时间内交换其两棵子树

空间复杂度：O(N)。使用的空间由递归栈的深度决定，它等于当前节点在二叉树中的高度。在平均情况下，二叉树的高度与节点个数为对数关系，即 O(log⁡N)。而在最坏情况下，树形成链状，空间复杂度为 O(N)

#### 辅助队列

递归是一个从下往上的过程，我们可以使用一个队列（栈）来存储每一层的节点，从上往下的进行反转

```java
    public TreeNode mirrorTree(TreeNode root) {
        Queue<TreeNode> queue = new LinkedList<>();//使用队列来存储节点
        TreeNode p = root;
        while (p != null) {
            if (p.left != null) {
                queue.add(p.left);
            }
            if (p.right != null) {
                queue.add(p.right);
            }
            //交换左子树与右子树
            TreeNode target = p.right;
            p.right = p.left;
            p.left = target;
            if (queue.isEmpty()) {//队列为空说明每一个节点都访问了，退出循环
                break;
            }
            p = queue.poll();//出队，一层一层的遍历
        }
        return root;
    }
```

**复杂度分析：**

时间复杂度 ：O(N)， 其中 N 为二叉树的节点数量，建立二叉树镜像需要遍历树的所有节点，占用 O(N) 时间。

空间复杂度 ：O(N)，使用队列来存储每一层的节点

## 剑指 Offer 28. 对称的二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/SymmetricBinaryTree.java)

请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。

**示例 1：**

```
输入：root = [1,2,2,3,4,4,3]
输出：true
```

**示例 2：**

```
输入：root = [1,2,2,null,3,null,3]
输出：false
```

 

**限制：**

```
0 <= 节点个数 <= 1000
```

### 题解

#### 递归

两个树互为镜像则它们的两个根结点具有相同的值且每个树的右子树都与另一个树的左子树镜像对称

我们可以定义两个指针p，q都指向root，将p的左子树与q的右子树比较，p的右子树与q的左子树比较，每一个节点都会与对应的位置进行比较

```java
    public boolean isSymmetric(TreeNode root){
        return check(root,root);
    }

    public boolean check(TreeNode p, TreeNode q){
        if(p==null&&q==null){//同时为null
            return true;
        }
        if(p==null||q==null){//只有一个为null肯定不符合条件
            return false;
        }

        //比较节点的值，p的左子树与q的右子树，p的右子树与q的左子树
        return p.val==q.val&&check(p.left,q.right)&&check(p.right,q.left);
    }
```

**复杂度分析：**

时间复杂度：O(n)，需要遍历整棵树，渐进时间复杂度为 O(n)

空间复杂度：O(n)，这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 n，故渐进空间复杂度为 O(n)

#### 辅助队列

同样不使用递归，也可以使用队列来进行辅助遍历

```java
    public boolean isSymmetric2(TreeNode root){
        Queue<TreeNode> queue = new LinkedList<>();//队列
        TreeNode p = root;
        TreeNode q = root;
        //先将根节点入队
        queue.offer(p);
        queue.offer(q);
        while (!queue.isEmpty()){
            //出队
            p = queue.poll();
            q = queue.poll();
            if(p==null&&q==null){//为空则跳过本轮循环
                continue;
            }
            if(p==null||q==null||p.val!=q.val){//值不相同或只有一方为空，返回false
                return false;
            }
            //p的左子树对应q的右子树；p的右子树对应q的左子树
            queue.offer(p.left);
            queue.offer(q.right);
            queue.offer(p.right);
            queue.offer(q.left);
        }
        return true;
    }
```

**复杂度分析：**

时间复杂度：O(n)

空间复杂度：O(n)，这里需要用一个队列来维护节点，每个节点最多进队一次，出队一次，队列中最多不会超过 n 个点，故渐进空间复杂度为 O(n)

## 剑指 Offer 29. 顺时针打印矩阵

### 题目描述

[原题链接](https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PrintTheMatrixClockwise.java)

输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。

 

**示例 1：**

```
输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
输出：[1,2,3,6,9,8,7,4,5]
```

**示例 2：**

```
输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
输出：[1,2,3,4,8,12,11,10,9,5,6,7]
```

 

**限制：**

- `0 <= matrix.length <= 100`
- `0 <= matrix[i].length <= 100`

### 题解

#### 模拟

我们从矩阵的`(0,0)`位置开始出发，先向右移动，到达右边界边界后向下移动，到达下边界后向左移动，到达左边界后向上移动，重复此过程。到达边界的同时我们需要修改对应的边界。

向右移动到达右边界后向下移动，我们需要修改上边界，因为上边界我们已经访问过了

```java
    public int[] spiralOrder(int[][] matrix) {
        if(matrix==null||matrix.length==0){
            return new int[0];
        }

        int[] result = new int[matrix.length* matrix[0].length];
        int upperBound = 0;//上边界
        int lowerBound = matrix.length-1;//下边界
        int leftSideBoundary = 0;//左边界
        int rightSideBoundary = matrix[0].length-1;//右边界
        int left=0,right=0;//坐标
        int direction =matrix[0].length==1?1:0;//移动方向
        int i=0;
        while (i != result.length) {
            result[i++] = matrix[left][right];
            switch (direction) {//确定移动方向
                case 0://向右移动
                    right++;
                    if (right == rightSideBoundary) {//到达右边界
                        upperBound++;//修改上边界
                        direction = 1;//下一步向下移动
                    }
                    break;
                case 1://向下移动
                    left++;
                    if (left == lowerBound) {//到达下边界
                        rightSideBoundary--;//修改右边界
                        direction = 2;//下一步向左移动
                    }
                    break;
                case 2://向左移动
                    right--;
                    if (right == leftSideBoundary) {//达到左边界
                        lowerBound--;//修改下边界
                        direction = 3;//下一步向上移动
                    }
                    break;
                case 3://向上移动
                    left--;
                    if (left == upperBound) {//达到上边界
                        leftSideBoundary++;//修改左边界
                        direction = 0;//下一步向右移动
                    }
                    break;
            }
        }

        return result;
    }
```

**复杂度分析：**

时间复杂度：O(m*n)，需要遍历数组的每一个元素

空间复杂度：O(1)，除了返回的数组只需要使用若干变量

## 剑指 Offer 30. 包含min函数的栈

### 题目描述

[原题链接](https://leetcode.cn/problems/bao-han-minhan-shu-de-zhan-lcof/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/TheStackContainingTheMinFunction.java)

定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，调用 min、push 及 pop 的时间复杂度都是 O(1)。

 

**示例:**

```
MinStack minStack = new MinStack();
minStack.push(-2);
minStack.push(0);
minStack.push(-3);
minStack.min();   --> 返回 -3.
minStack.pop();
minStack.top();      --> 返回 0.
minStack.min();   --> 返回 -2.
```

 

### 题解

与普通的栈不同我们需要提供获取栈最小值的函数。最小值有可能会因为出栈和入栈而发生改变，所以我们需要开辟额外的空间来存储每一阶段的最小值

```java
class MinStack {
    private final List<Integer> list;
    private final List<Integer> min;

    /** initialize your data structure here. */
    public MinStack() {
        list = new ArrayList<>();//存储栈元素
        min = new ArrayList<>();//存储每一阶段最小值
    }

    public void push(int x) {//入栈
        list.add(x);//添加元素
        //添加这一阶段最小值
        if(min.size()==0){
            min.add(x);
        } else {
            min.add(Math.min(min.get(min.size()-1),x));
        }
    }

    public void pop() {//弹栈
        if(list.size()>0){
            //删除栈元素的同时需要删除这一阶段的最小值
            Integer remove = list.remove(list.size() - 1);
            min.remove(min.size()-1);
        }
    }

    public int top() {//获取栈顶元素
        if(list.size()>0){
            return list.get(list.size()-1);
        } else {
            return -1;
        }
    }

    public int min() {//获取最小值
        return min.get(min.size()-1);
    }
}
```

**复杂度分析：**

时间复杂度：O(1)

空间复杂度：O(n)

## 剑指 Offer 31. 栈的压入、弹出序列

### 题目描述

[原题链接](https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ThePushAndPopSequenceOfTheStack.java)

输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。

 

**示例 1：**

```
输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
输出：true
解释：我们可以按以下顺序执行：
push(1), push(2), push(3), push(4), pop() -> 4,
push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
```

**示例 2：**

```
输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
输出：false
解释：1 不能在 2 之前弹出。
```

 

**提示：**

1. `0 <= pushed.length == popped.length <= 1000`
2. `0 <= pushed[i], popped[i] < 1000`
3. `pushed` 是 `popped` 的排列。

### 题解

#### 模拟

`pushed`是入栈序列，`popped`是出栈序列。只有栈顶元素才能出栈，同一元素入栈和出栈都只有一次机会。所有元素都出栈后栈就为空。

我们可以模拟`pushed`入栈，并判断栈顶元素是否需要出栈（因为压入栈的所有数字均不相等，所以可以根据`popped`来进行判断）

最后如果栈空则说明按照所给的序列进行入栈和出栈所有的元素都完成了入栈和出栈的操作，返回true

```java
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();
        int index = 0;
        for (int num : pushed) {
            stack.push(num);//入栈
            while (!stack.isEmpty() && stack.peek() == popped[index]) {//判断是否可以出栈
                stack.pop();
                index++;
            }
        }
        return stack.isEmpty();//若栈空则符合要求
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是数组 pushed 和 popped 的长度。需要遍历数组 pushed 和 popped 各一次，判断两个数组是否为有效的栈操作序列。

空间复杂度：O(n)，其中 n 是数组 pushed 的长度。空间复杂度主要取决于栈空间，栈内元素个数不超过 n。

## 剑指 Offer 32 - I. 从上到下打印二叉树

### 题目描述

[原题链接](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PrintABinaryTreeFromTopToBottom.java)

从上到下打印出二叉树的每个节点，同一层的节点按照从左到右的顺序打印。

 

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回：

```
[3,9,20,15,7]
```

### 题解

#### 层次遍历

使用队列来存储每一层的节点进行层次遍历，将节点值暂存在集合中，返回前再将集合转成数组

```java
    public int[] levelOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();//队列
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            list.add(node.val);
            if (node.left != null) {//添加左节点
                queue.add(node.left);
            }
            if (node.right != null) {//添加右节点
                queue.add(node.right);
            }
        }
        int[] result = new int[list.size()];
        int index = 0;
        for (Integer num : list) {//集合转数组
            result[index++] = num;
        }
        return result;
    }
```

**复杂度分析：**

时间复杂度 O(N) ： N 为二叉树的节点数量，每一个节点都会访问到

空间复杂度 O(N) ： 最差情况下，即当树为平衡二叉树时，最多有 N/2 个树节点同时在 queue 中，使用 O(N)大小的额外空间，数组的空间不算

## 剑指 Offer 32 - II. 从上到下打印二叉树 II

### 题目描述

[原题链接](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-ii-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PrintABinaryTreeFromTopToBottomII.java)

从上到下按层打印二叉树，同一层的节点按从左到右的顺序打印，每一层打印到一行。

 

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [9,20],
  [15,7]
]
```

 

**提示：**

1. `节点总数 <= 1000`

### 题解

#### 层次遍历（计数+迭代）

与普通的层次遍历不同，根据题目要求我们需要分层。所以可以设置一个变量来记录队列的元素个数进而来维护每一层的节点个数，根据这个变量去迭代，取元素，添加新的元素就可以达到分层的目的。

```java
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();//队列
        if (root != null)
            queue.add(root);
        while (!queue.isEmpty()){//队列不为空
            int size = queue.size();//队列元素个数（一层的元素个数）
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < size; i++) {//遍历这一层
                TreeNode node = queue.poll();
                nums.add(node.val);
                //添加下一层的元素
                if(node.left!=null){
                    queue.add(node.left);
                }
                if(node.right!=null){
                    queue.add(node.right);
                }
            }
            list.add(nums);//添加这一层的元素
        }
        return list;
    }
```

**复杂度分析：**

时间复杂度： O(n)，每个点进队出队各一次。

空间复杂度：O(n)，队列中元素的个数不超过 n 个

也可以维护两个队列firstQueue与secondQueue，firstQueue表示这一层节点用于遍历，secondQueue来表示下一层节点用于添加元素，当firstQueue为空时，添加元素集合到返回集合中并交换两个队列的句柄

```java
public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        List<Integer> nums = new ArrayList<>();
        Queue<TreeNode> firstQueue = new LinkedList<>();//用于遍历
        Queue<TreeNode> secondQueue = new LinkedList<>();//用于添加元素
        if (root != null)
            firstQueue.add(root);
        while (!firstQueue.isEmpty()) {
            TreeNode node = firstQueue.poll();
            nums.add(node.val);
            if (node.left != null) {
                secondQueue.add(node.left);
            }
            if (node.right != null) {
                secondQueue.add(node.right);
            }
            if (firstQueue.isEmpty()) {
                list.add(new ArrayList<>(nums));
                nums.clear();
                //交换队列
                Queue<TreeNode> t = firstQueue;
                firstQueue = secondQueue;
                secondQueue = t;
            }
        }
        return list;
    }
```

这个方法不管是时间复杂度还是空间复杂度都要比上一种的高。使用了两个队列并且`list.add(new ArrayList<>(nums));`需要进行数组拷贝

## 剑指 Offer 32 - III. 从上到下打印二叉树 III

### 题目描述

[原题链接](https://leetcode.cn/problems/cong-shang-dao-xia-da-yin-er-cha-shu-iii-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PrintABinaryTreeFromTopToBottomIII.java)

请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，第三行再按照从左到右的顺序打印，其他行以此类推。

 

例如:
给定二叉树: `[3,9,20,null,null,15,7]`,

```
    3
   / \
  9  20
    /  \
   15   7
```

返回其层次遍历结果：

```
[
  [3],
  [20,9],
  [15,7]
]
```

 

**提示：**

1. `节点总数 <= 1000`

### 题解

#### 广度优先遍历

与层次遍历类似，不过相邻两层打印顺序相反，第一层按照从左到右的顺序打印，第二层按照从右到左的顺序打印...

我们可以在保存每一层数据时来实现区分，如从左往右遍历，则将数据从尾部插入；从右往左遍历，则将数据从头部插入

```java
    public List<List<Integer>> levelOrder2(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }
        boolean flag = true;//标记打印顺序，true为从左往右
        while (!queue.isEmpty()) {
            int size = queue.size();
            Deque<Integer> deque = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (flag) {
                    deque.offerLast(node.val);//尾插
                } else {
                    deque.offerFirst(node.val);//头插
                }
                //添加下一层节点
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
            }
            list.add(new LinkedList<>(deque));
            flag = !flag;//改变方向
        }
        return list;
    }
```

**复杂度分析：**

时间复杂度：O(N)，其中 N 为二叉树的节点数。每个节点会且仅会被遍历一次。

空间复杂度：O(N)，我们需要维护存储节点的队列和存储节点值的双端队列，空间复杂度为 O(N)

同样也可以在通过改变遍历的方向来实现，不过改变遍历方向的同时也需要相应的改变添加下一层节点的位置

```java
    public List<List<Integer>> levelOrder(TreeNode root) {
        List<TreeNode> linkedList = new LinkedList<>();
        List<List<Integer>> list = new ArrayList<>();
        boolean flag = true;
        if (root != null)
            linkedList.add(root);
        while (!linkedList.isEmpty()) {
            int size = linkedList.size();
            List<Integer> nums = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node;
                if (flag) {//从左往右
                    node = linkedList.remove(0);//获取并删除头节点
                    //添加下一层节点
                    if (node.left != null) {
                        linkedList.add(node.left);
                    }
                    if (node.right != null) {
                        linkedList.add(node.right);
                    }
                } else {//从右往左
                    node = linkedList.remove(size - i - 1);//获取并删除尾部节点
                    //添加下一层节点，头插
                    if (node.right != null) {
                        linkedList.add(size - i - 1, node.right);
                    }
                    if (node.left != null) {
                        linkedList.add(size - i - 1, node.left);
                    }
                }
                nums.add(node.val);
            }
            list.add(nums);
            flag = !flag;//改变方向
        }
        return list;
    }
```

时间复杂度和空间复杂度比上一种方法都要高一些，`linkedList.add(size - i - 1, node.left);`会产生额外的复杂度，不过不会太多

## 剑指 Offer 33. 二叉搜索树的后序遍历序列

### 题目描述

### 题解

[原题链接](https://leetcode.cn/problems/er-cha-sou-suo-shu-de-hou-xu-bian-li-xu-lie-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/PostOrderTraversalSequenceOfBinarySearchTree.java)

输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 `true`，否则返回 `false`。假设输入的数组的任意两个数字都互不相同。

 

参考以下这颗二叉搜索树：

```
     5
    / \
   2   6
  / \
 1   3
```

**示例 1：**

```
输入: [1,6,3,2,5]
输出: false
```

**示例 2：**

```
输入: [1,3,2,6,5]
输出: true
```

 

**提示：**

1. `数组长度 <= 1000`

#### 递归分治

对于二叉搜索树的后序遍历，最后访问的节点一定是根节点，我们可以根据这个根节点将数组划分为左子树与右子树两个区域，左子树区域所有节点值一定小于根节点，右子树的所有节点值一定大于根节点的值。同样对于左子树与右子树区域也存在同样的性质，区域内数组的最后一个元素为根节点，左子树与右子树同样也是二叉搜索树。

所以我们要做的就是

* 找到区域数组最后一个元素即为根节点
* 从左往右遍历区域内的数组找到边界（可以以第一个大于根节点的元素为边界）
* 根据边界划分出两个区域，判断这两个区域是否合法，左区域节点值应小于根节点，右区域节点值应大于根节点
* 同样的方法递归判断这两个区域

```java
    public boolean verifyPostorder(int[] postorder) {
        return check(postorder,0,postorder.length-1);
    }

    public boolean check(int[] postorder,int start,int end){
        if(start>=end){//划分的区域只有一个元素或者越界了
            return true;
        }
        int index = start;
        while (postorder[index]<postorder[end]){//从左往右寻找边界
            index++;
        }
        int i = index;
        while (postorder[i]>postorder[end]){//判断右区域是否合法
            i++;
        }
        return i==end&&check(postorder,start,index-1)&&check(postorder,index,end-1);//递归判断划分的两个区域
    }
```

**复杂度分析：**

时间复杂度：O(N^2)， 每次调用 `check()` 减去一个根节点，因此递归占用 O(N) ；最差情况下（即当树退化为链表），每轮递归都需遍历树所有节点，占用 O(N)

空间复杂度 ：O(N)最差情况下（即当树退化为链表），递归深度将达到 N 

#### 辅助栈

利用一个栈空间来尝试根据后序后序遍历数组构建一二叉搜索树。

后序遍历，访问顺序为左->右->中，不利于我们构建。如果倒序遍历数组，则访问顺序为中->右->左，我们可以很方便的找到先找到根节点（遍历的第一个元素就是），并且如果后一个元素大于根节点，则他一定为右子树的根节点，如果他小于根节点则一定位于左子树。

对于二叉搜索树，往右子树遍历的过程，value是越来越大的，一旦出现了value小于栈顶元素value的时候，就表示要开始进入左子树了（如果不是，就应该继续进入右子树，否则不满足二叉搜索树的定义），但是这个左子树是从哪个节点开始的呢

我们可以用一个栈来记录了这些节点，当遇见value小于栈顶元素value的时候，我们就弹栈来寻找父节点，只要栈顶元素还比当前节点大，就表示还是右子树，要移除，因为我们要找到这个左孩子节点直接连接的父节点，也就是找到这个子树的根，只要栈顶元素还大于当前节点，就要一直弹出，直到栈顶元素小于节点，或者栈为空。栈顶的上一个元素就是子树节点的根。

接下来，数组继续往前遍历，之后的左子树的每个节点，都要比子树的根要小，才能满足二叉搜索树，否则就不是二叉搜索树。

```java
    public boolean verifyPostorder2(int[] postorder) {
        Stack<Integer> stack = new Stack<>();
        //表示上一个根节点的元素
        int root = Integer.MAX_VALUE;
        for (int i = postorder.length - 1; i >= 0; i--) {
            if (postorder[i] > root) return false;//左子树的每个节点，都要比子树的根要小
            while (!stack.isEmpty() && stack.peek() > postorder[i])
                // 数组元素小于单调栈的元素了，表示往左子树走了，记录下上个根节点
                // 找到这个左子树对应的根节点，之前右子树全部弹出，不再记录，因为不可能在往根节点的右子树走
                root = stack.pop();
            stack.add(postorder[i]);//这个新元素入栈
        }
        return true;
    }
```

**复杂度分析：**

时间复杂度：O(N)，遍历 postorder 所有节点，各节点均入栈 / 出栈一次，使用 O(N) 时间

空间复杂度：O(N)， 最差情况下，单调栈 stack 存储所有节点，使用 O(N) 额外空间

## 剑指 Offer 34. 二叉树中和为某一值的路径

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ABinaryTreeIsAPathWithAValue.java)

给你二叉树的根节点 `root` 和一个整数目标和 `targetSum` ，找出所有 **从根节点到叶子节点** 路径总和等于给定目标和的路径。

**叶子节点** 是指没有子节点的节点。

 

**示例 1：**

![img](https://assets.leetcode.com/uploads/2021/01/18/pathsumii1.jpg)

```
输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
输出：[[5,4,11,2],[5,8,4,5]]
```

**示例 2：**

![img](https://assets.leetcode.com/uploads/2021/01/18/pathsum2.jpg)

```
输入：root = [1,2,3], targetSum = 5
输出：[]
```

**示例 3：**

```
输入：root = [1,2], targetSum = 0
输出：[]
```

 

**提示：**

- 树中节点总数在范围 `[0, 5000]` 内
- `-1000 <= Node.val <= 1000`
- `-1000 <= targetSum <= 1000`

### 题解

#### 深度优先（递归）

先序遍历每一个节点，当达到叶子节点时判断路径是否符合要求，回溯后需要恢复到初始条件

```java
public List<List<Integer>> pathSum(TreeNode root, int target) {
        route(root, target);
        return list;
    }

    int sum;//路径和
    List<List<Integer>> list = new ArrayList<>();//路径节点信息
    List<Integer> record = new ArrayList<>();//返回结果

    public void route(TreeNode root, int target) {
        if (root == null) {
            return;
        }
        //添加节点
        sum += root.val;
        record.add(root.val);
        if (root.left == null && root.right == null) {//叶子节点
            if (sum == target) {//符合要求
                list.add(new ArrayList<>(record));
            }
            return;
        }
        if (root.left != null) {//先序遍历左子树
            route(root.left, target);
            //恢复初始条件
            sum -= root.left.val;
            record.remove(record.size() - 1);
        }
        if (root.right != null) {//先序遍历右子树
            route(root.right, target);
            //恢复初始条件
            sum -= root.right.val;
            record.remove(record.size() - 1);
        }
    }
```

**复杂度分析：**

时间复杂度：O(N^2)，其中 N 是树的节点数。在最坏情况下，树的上半部分为链状，下半部分为完全二叉树，并且从根节点到每一个叶子节点的路径都符合题目要求。此时，路径的数目为 O(N)，并且每一条路径的节点个数也为 O(N)，因此要将这些路径全部添加进答案中，时间复杂度为 O(N^2)

空间复杂度：O(N)，其中 N 是树的节点数。空间复杂度主要取决于栈空间的开销，栈中的元素个数不会超过树的节点数。

#### 广度优先

参考：[宫水三叶](https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solutions/1721944/by-ac_oier-3ehr/)

使用 BFS 的话，我们需要封装一个类/结构体 Node，该结构体存储所对应的原始节点 node，到达 node 所经过的路径 list，以及对应的路径和 tot。

由于 BFS 过程并非按照路径进行（即相邻出队的节点并非在同一路径），因此我们每次创建新的 Node 对象时，需要对路径进行拷贝操作。

```java
    class Node {
        TreeNode node;
        List<Integer> list;//到达 node 所经过的路径
        int tot;//对应路径的和

        Node(TreeNode _node, List<Integer> _list, int _tot) {//路径拷贝
            node = _node;
            list = new ArrayList<>(_list);
            tot = _tot;
            list.add(node.val);
            tot += node.val;
        }
    }

    public List<List<Integer>> pathSum2(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Deque<Node> d = new ArrayDeque<>();
        if (root != null)
            d.addLast(new Node(root, new ArrayList<>(), 0));
        while (!d.isEmpty()) {
            Node t = d.pollFirst();
            if (t.tot == target && t.node.left == null && t.node.right == null)
                ans.add(t.list);
            if (t.node.left != null)
                d.addLast(new Node(t.node.left, t.list, t.tot));
            if (t.node.right != null)
                d.addLast(new Node(t.node.right, t.list, t.tot));
        }
        return ans;
    }
```

**复杂度分析：**

时间复杂度：O(N^2)，其中 N 是树的节点数

空间复杂度：O(N)，其中 N 是树的节点数

## 剑指 Offer 35. 复杂链表的复制

### 题目描述

[原题链接](https://leetcode.cn/problems/fu-za-lian-biao-de-fu-zhi-lcof/description/?favorite=xb9nqhhg)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/ReplicationOfComplexLinkedLists.java)

请实现 `copyRandomList` 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 `next` 指针指向下一个节点，还有一个 `random` 指针指向链表中的任意节点或者 `null`。

 

**示例 1：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e1.png)

```
输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
```

**示例 2：**

![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e2.png)

```
输入：head = [[1,1],[2,1]]
输出：[[1,1],[2,1]]
```

**示例 3：**

**![img](https://assets.leetcode-cn.com/aliyun-lc-upload/uploads/2020/01/09/e3.png)**

```
输入：head = [[3,null],[3,0],[3,null]]
输出：[[3,null],[3,0],[3,null]]
```

**示例 4：**

```
输入：head = []
输出：[]
解释：给定的链表为空（空指针），因此返回 null。
```

 

**提示：**

- `-10000 <= Node.val <= 10000`
- `Node.random` 为空（null）或指向链表中的节点。
- 节点数目不超过 1000 。

### 题解

#### 哈希表

第一次遍历使用哈希表来存储目标链表节点与新链表节点的映射关系，第二次遍历根据哈希表存储的映射关系组装节点

```java
    Map<Node, Node> map = new HashMap<>();//哈希表存储映射关系
    public Node copyRandomList(Node head) {
        Node p = head;
        while (p != null) {
            map.put(p, new Node(p.val));//添加映射关系
            p = p.next;
        }
        p = head;
        while (p != null) {//组装节点
            Node node = map.get(p);
            node.next = map.get(p.next);
            node.random = map.get(p.random);
            p = p.next;
        }
        return map.get(head);//返回头指针
    }

```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是链表的长度

空间复杂度：O(n)，其中 n 是链表的长度。为哈希表的空间开销

同样，我们也可以用哈希表+递归的方式来实现。在创建节点前我们需要判断是否已经创建了相关节点

```java
    Map<Node, Node> map = new HashMap<>();//哈希表存储映射关系
    public Node copyRandomList2(Node head) {
        if (head == null) {//为空直接返回
            return null;
        }

        if (!map.containsKey(head)) {//哈希表中没有对应的映射关系
            Node node = new Node(head.val);//创建新节点
            map.put(head, node);//添加映射关系
            node.next = copyRandomList2(head.next);//递归构建相关的next指针指向的节点
            node.random = copyRandomList2(head.random);//递归构建相关的random指针指向的节点
        }
        return map.get(head);//返回创建的节点
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是链表的长度。对于每个节点，我们至多访问其「后继节点」和「随机指针指向的节点」各一次，均摊每个点至多被访问两次

空间复杂度：O(n)，其中 n 是链表的长度。为哈希表的空间开销

#### 迭代 + 节点拆分

我们使用哈希表是为了保存旧节点与新节点的映射关系，方便为next指针与random赋值时能通过旧节点找到新节点。

有没有办法不使用哈希表？

我们可以将创建的新节点链接到对应的旧旧节点后，保证旧节点的next指针指向的是对应的新节点，新节点的next指针指向的是旧节点后的那一个旧节点。简单来说就是在旧节点之间插入一个新节点

采用官方的图

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-12-04_13-41-01.png)

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-12-04_13-43-36.png)

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-12-04_13-41-37.png)

![](https://dar-1305869431.cos.ap-shanghai.myqcloud.com/algorithm/Snipaste_2022-12-04_13-41-44.png)

```java
    public Node copyRandomList3(Node head) {
        if (head == null) {
            return null;
        }

        for (Node node = head; node != null; node = node.next.next) {//插入新节点
            Node newNode = new Node(node.val);
            newNode.next = node.next;
            node.next = newNode;
        }

        for (Node node = head; node != null; node = node.next.next) {//更新random指针
            Node newNode = node.next;
            newNode.random = node.random == null ? null : node.random.next;
        }

        Node newHead = head.next;
        for (Node node = head; node != null; node = node.next) {//连接新节点
            Node nodeNew = node.next;
            node.next = node.next.next;
            nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next : null;
        }
        return newHead;
    }
```

**复杂度分析：**

时间复杂度：O(n)，其中 n 是链表的长度。我们只需要遍历该链表三次

空间复杂度：O(1)，返回值不计入空间复杂度

## 剑指 Offer 36. 二叉搜索树与双向链表

### 题目描述

[原题链接](https://leetcode.cn/problems/er-cha-sou-suo-shu-yu-shuang-xiang-lian-biao-lcof/description/)

[测试代码](https://github.com/dar02kon/LeetCode/blob/master/src/com/dar/leetcode/the_sword_refers_to_offer/BinarySearchTreesAndBidirectionalLinkedLists.java)

输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的循环双向链表。要求不能创建任何新的节点，只能调整树中节点指针的指向。

 

为了让您更好地理解问题，以下面的二叉搜索树为例：

 

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdlloriginalbst.png)

 

我们希望将这个二叉搜索树转化为双向循环链表。链表中的每个节点都有一个前驱和后继指针。对于双向循环链表，第一个节点的前驱是最后一个节点，最后一个节点的后继是第一个节点。

下图展示了上面的二叉搜索树转化成的链表。“head” 表示指向链表中有最小元素的节点。

 

![img](https://assets.leetcode.com/uploads/2018/10/12/bstdllreturndll.png)

 

特别地，我们希望可以就地完成转换操作。当转化完成以后，树中节点的左指针需要指向前驱，树中节点的右指针需要指向后继。还需要返回链表中的第一个节点的指针。

### 题解

### 中序遍历

二叉搜索树的中序遍历结果就是从小到大的排序，可以先进行一次中序遍历并使用集合将节点存储起来，然后再遍历集合连接节点

```java
    public Node treeToDoublyList(Node root) {
        transfer(root);
        for (int i = 0; i < list.size(); i++) {//连接节点
            Node node = list.get(i);
            node.left = i - 1 == -1 ? list.get(list.size() - 1) : list.get(i - 1);
            node.right = i + 1 == list.size() ? list.get(0) : list.get(i + 1);
        }
        return list.size() == 0 ? null : list.get(0);
    }

    List<Node> list = new ArrayList<>();

    public void transfer(Node root) {//中序遍历
        if (root == null) {
            return;
        }
        transfer(root.left);//访问左子树
        list.add(root);//访问根节点
        transfer(root.right);//访问右子树
    }
```

**复杂度分析：**

时间复杂度：O(N)

空间复杂度：O(N) 

也可以不使用集合存储节点，不过需要使用两个变量来记录前驱和头节点

```java
    Node head, pre;//记录头节点和前驱
    public Node treeToDoublyList2(Node root) {
        transfer2(root);
        //头尾相连
        head.left = pre;
        pre.right = head;
        return head;
    }

    public void transfer2(Node root) {//中序遍历
        if (root == null) {
            return;
        }
        transfer2(root.left);//访问左子树
        if (pre == null) {//为空说明是头节点
            head = root;
        } else {//连接节点
            root.left = pre;
            pre.right = root;
        }
        pre = root;//更新前驱节点指针
        transfer2(root.right);//访问右子树
    }
```

**复杂度分析：**

时间复杂度：O(N)，N 为二叉树的节点数，中序遍历需要访问所有节点

空间复杂度：O(N)，最差情况下，即树退化为链表时，递归深度达到 N，系统使用 O(N) 栈空间
