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

![](D:\学习历程\Java\刷题\img\剑指offer\Snipaste_2022-11-23_18-59-26.png)

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
