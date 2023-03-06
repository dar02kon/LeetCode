# SQL训练

## 基础查询

### 基础查询

#### 查询所有列

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/f9f82607cac44099a77154a80266234a?tpId=199&tqId=1971219&ru=/exam/oj&qru=/ta/sql-quick-study/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要查看用户信息表中所有的数据，请你取出相应结果

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据示例，你的查询应返回以下结果:

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | Zhejiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |



输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
1|2138|male|21|北京大学|BeiJing
2|3214|male|None|复旦大学|Shanghai
3|6543|female|20|北京大学|BeiJing
4|2315|female|23|浙江大学|ZheJiang
5|5432|male|25|山东大学|Shandong
```

##### 题解

对于全列查询尽量还是使用列名进行查询

```sql
SELECT id,device_id,gender,age,university,province FROM user_profile
```

#### 查询多列

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/499b6d01eae342b2aaeaf4d0da61cab0?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营同学想要用户的设备id对应的性别、年龄和学校的数据，请你取出相应数据

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | Zhejiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据示例，你的查询应返回以下结果

| device_id | gender | age  | university |
| --------- | ------ | ---- | ---------- |
| 2138      | male   | 21   | 北京大学   |
| 3214      | male   |      | 复旦大学   |
| 6543      | female | 20   | 北京大学   |
| 2315      | female | 23   | 浙江大学   |
| 5432      | male   | 25   | 山东大学   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
device_id|gender|age|university
2138|male|21|北京大学
3214|male|None|复旦大学
6543|female|20|北京大学
2315|female|23|浙江大学
5432|male|25|山东大学
```

##### 题解

查询需要的列即可

```sql
SELECT device_id,gender,age,university FROM user_profile
```

### 简单处理查询结果

#### 查询结果去重

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/82ebd89f12cf48efba0fecb392e193dd?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营需要查看用户来自于哪些学校，请从用户信息表中取出学校的去重数据。

示例:user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据示例，你的查询应返回以下结果：

| university |
| ---------- |
| 北京大学   |
| 复旦大学   |
| 浙江大学   |
| 山东大学   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
北京大学
复旦大学
浙江大学
山东大学
```

##### 题解

使用`DISTINCT`关键字进行去重，`DISTINCT`必须放在要查询字段的开头，表示对后面的所有参数的拼接取不重复的记录，即查出的参数拼接每行记录都是唯一的

```sql
SELECT DISTINCT
    university
FROM
    user_profile
```

使用`GROUP BY `分组去重

```sql
SELECT
    university
FROM
    user_profile
GROUP BY
    university
```

#### 查询结果限制返回行数

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/c7ad0e2df4f647dfa5278e99894a7561?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营只需要查看前2个用户明细设备ID数据，请你从用户信息表 user_profile 中取出相应结果。

示例：

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据输入，你的查询应返回以下结果：

| device_id |
| --------- |
| 2138      |
| 3214      |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138
3214
```

##### 题解

使用`LIMIT`限制结果集，`LIMIT` 子句可以被用于强制 `SELECT` 语句返回指定的记录数。
`LIMIT` 接受一个或两个数字参数。参数必须是一个整数常量。

* 如果只给定一个参数，它表示返回最大的记录行数目。
* 如果给定两个参数，第一个参数指定第一个返回记录行的偏移量，第二个参数指定返回记录行的最大数目。

为了检索从某一个偏移量到记录集的结束所有的记录行，可以指定第二个参数为 -1。
初始记录行的偏移量是 0(而不是 1)。

```sql
SELECT
    device_id
FROM
    user_profile
LIMIT
    2
```

或

```sql
SELECT
    device_id
FROM
    user_profile
LIMIT
    0, 2
```

#### 将查询后的列重新命名

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/0d8f49aeaf7a4e1cb7fecec980712113?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在你需要查看前2个用户明细设备ID数据，并将列名改为 'user_infos_example',，请你从用户信息表取出相应结果。

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据示例，你的查询应返回以下结果：

| user_infos_example |
| ------------------ |
| 2138               |
| 3214               |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138
3214
```

##### 题解

取别名（`AS`加不加都可以）

```sql
SELECT
    device_id AS user_infos_example
FROM
    user_profile
LIMIT
    2
```

## 条件查询

### 基础排序

####  查找后排序

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/cd4c5f3a64b4411eb4810e28afed6f54?tpId=199&tqId=2002632&ru=/exam/oj&qru=/ta/sql-quick-study/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要取出用户信息表中的用户年龄，请取出相应数据，并按照年龄升序排序。

示例：user_profile

| id   | device_id | gender | age  | university   | gpa  |
| ---- | --------- | ------ | ---- | ------------ | ---- |
| 1    | 2138      | male   | 21   | 北京大学     | 3.4  |
| 2    | 3214      | male   | 23   | 复旦大学     | 4    |
| 3    | 6543      | female | 20   | 北京大学     | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学     | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学     | 3.8  |
| 6    | 2131      | male   | 28   | 北京师范大学 | 3.3  |

根据示例，你的查询应返回以下结果：

| device_id | age  |
| --------- | ---- |
| 6534      | 20   |
| 2138      | 21   |
| 3214      | 23   |
| 2315      | 23   |
| 5432      | 25   |
| 2131      | 28   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',23,'复旦大学',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8);
INSERT INTO user_profile VALUES(6,2131,'male',28,'北京师范大学',3.3);
```

输出：

```
6543|20
2138|21
3214|23
2315|23
5432|25
2131|28
```

##### 题解

使用`ORDER BY`进行排序，`ASC`为升序（默认），`DESC`为降序

```sql
SELECT
    device_id,
    age
FROM
    user_profile
ORDER BY
    age ASC
```

#### 查找后多列排序

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/39f74706f8d94d37865a82ffb7ba67d3?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

用户信息表：user_profile

| id   | device_id | gender | age  | university   | gpa  |
| ---- | --------- | ------ | ---- | ------------ | ---- |
| 1    | 2138      | male   | 21   | 北京大学     | 3.4  |
| 2    | 3214      | male   | 23   | 复旦大学     | 4    |
| 3    | 6543      | female | 20   | 北京大学     | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学     | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学     | 3.8  |
| 6    | 2131      | male   | 28   | 北京师范大学 | 3.3  |

你的查询应返回以下结果：

| device_id | gpa  | age  |
| --------- | ---- | ---- |
| 6534      | 3.2  | 20   |
| 2131      | 3.3  | 28   |
| 2138      | 3.4  | 21   |
| 2315      | 3.6  | 23   |
| 5432      | 3.8  | 25   |
| 3214      | 4    | 23   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',23,'复旦大学',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8);
INSERT INTO user_profile VALUES(6,2131,'male',28,'北京师范大学',3.3);
```

输出：

```
6543|3.200|20
2131|3.300|28
2138|3.400|21
2315|3.600|23
5432|3.800|25
3214|4.000|23
```

##### 题解

使用`ORDER BY`进行多列排序

```sql
SELECT
    device_id,
    gpa,
    age
FROM
    user_profile
ORDER BY
    gpa,
    age
```

#### 查找后降序排列

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/d023ae0191e0414ca1b19451099a39f1?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要取出用户信息表中对应的数据，并先按照gpa、年龄降序排序输出，请取出相应数据。

示例 user_profile：

| id   | device_id | gender | age  | university   | gpa  |
| ---- | --------- | ------ | ---- | ------------ | ---- |
| 1    | 2138      | male   | 21   | 北京大学     | 3.4  |
| 2    | 3214      | male   | 23   | 复旦大学     | 4    |
| 3    | 6543      | female | 20   | 北京大学     | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学     | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学     | 3.8  |
| 6    | 2131      | male   | 28   | 北京师范大学 | 3.3  |

根据示例，你的查询应返回以下结果：

| device_id | gpa  | age  |
| --------- | ---- | ---- |
| 3214      | 4    | 23   |
| 5432      | 3.8  | 25   |
| 2315      | 3.6  | 23   |
| 2138      | 3.4  | 21   |
| 2131      | 3.3  | 28   |
| 6543      | 3.2  | 20   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',23,'复旦大学',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8);
INSERT INTO user_profile VALUES(6,2131,'male',28,'北京师范大学',3.3);
```

输出：

```
3214|4.0|23
5432|3.8|25
2315|3.6|23
2138|3.4|21
2131|3.3|28
6543|3.2|20
```

##### 题解

使用`ORDER BY`+`DESC`即可完成降序排序

```sql
SELECT
    device_id,
    gpa,
    age
FROM
    user_profile
ORDER BY
    gpa DESC,
    age DESC
```

### 基础操作符

#### 查找学校是北大的学生信息

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/7858f3e234bc4d85b81b9a6c3926f49f?tpId=199&tqId=1971248&ru=%2Fpractice%2Fb8f30b239b454ed490367b53ea95607d&qru=%2Fta%2Fsql-quick-study%2Fquestion-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要筛选出所有北京大学的学生进行用户调研，请你从用户信息表中取出满足条件的数据，结果返回设备id和学校。

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据示例，你的查询应返回以下结果：

| device_id | university |
| --------- | ---------- |
| 2138      | 北京大学   |
| 6543      | 北京大学   |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138|北京大学
6543|北京大学
```

##### 题解

根据条件进行查询即可

```sql
SELECT
    device_id,
    university
FROM
    user_profile
WHERE
    university = '北京大学'
```

因为`university`其实是固定值，所以可以直接给出

```sql
SELECT
    device_id,
    '北京大学' university
FROM
    user_profile
WHERE
    university = '北京大学'
```

####  查找年龄大于24岁的用户信息

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/2ae16bf2fd54415f99344e6024470d57?tpId=199&tqId=1971384&ru=/exam/oj&qru=/ta/sql-quick-study/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要针对24岁以上的用户开展分析，请你取出满足条件的设备ID、性别、年龄、学校。

用户信息表：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |



根据输入，你的 查询应返回以下结果：

| device_id | gender | age  | university |
| --------- | ------ | ---- | ---------- |
| 5432      | male   | 25   | 山东大学   |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
5432|male|25|山东大学
```

##### 题解

使用`WHERE`过滤条件即可

```sql
SELECT
    device_id,
    gender,
    age,
    university
FROM
    user_profile
WHERE
    age > 24
```

#### 查找某个年龄段的用户信息

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/be54223075cc43ceb20e4ce8a8e3e340?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要针对20岁及以上且23岁及以下的用户开展分析，请你取出满足条件的设备ID、性别、年龄。

用户信息表：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据输入，你的查询应返回以下结果：

| device_id | gender | age  |
| --------- | ------ | ---- |
| 2138      | male   | 21   |
| 6543      | female | 20   |
| 2315      | female | 23   |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138|male|21
6543|female|20
2315|female|23
```

##### 题解

使用`BETWEEN AND`进行范围控制即可（包括自身）

```sql
SELECT
    device_id,
    gender,
    age
FROM
    user_profile
WHERE
    age BETWEEN 20 AND 23
```

#### 查找除复旦大学的用户信息

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/c12a056497404d1ea782308a7b821f9c?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要查看除复旦大学以外的所有用户明细，请你取出相应数据

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据输入，你的查询应返回以下结果：

| device_id | gender | age  | university |
| --------- | ------ | ---- | ---------- |
| 2138      | male   | 21   | 北京大学   |
| 6543      | female | 20   | 北京大学   |
| 2315      | female | 23   | 浙江大学   |
| 5432      | male   | 25   | 山东大学   |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138|male|21|北京大学
6543|female|20|北京大学
2315|female|23|浙江大学
5432|male|25|山东大学
```

##### 题解

使用不等条件即可

```sql
SELECT
    device_id,
    gender,
    age,
    university
FROM
    user_profile
WHERE
    university != '复旦大学'
```

#### 用where过滤空值练习

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/08c9846a423540319eea4be44e339e35?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要对用户的年龄分布开展分析，在分析时想要剔除没有获取到年龄的用户，请你取出所有年龄值不为空的用户的设备ID，性别，年龄，学校的信息。

示例：user_profile

| id   | device_id | gender | age  | university | province |
| ---- | --------- | ------ | ---- | ---------- | -------- |
| 1    | 2138      | male   | 21   | 北京大学   | Beijing  |
| 2    | 3214      | male   |      | 复旦大学   | Shanghai |
| 3    | 6543      | female | 20   | 北京大学   | Beijing  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong |

根据输入，你的 查询应返回以下结果：

| device_id | gender | age  | university |
| --------- | ------ | ---- | ---------- |
| 2138      | male   | 21   | 北京大学   |
| 6543      | female | 20   | 北京大学   |
| 2315      | female | 23   | 浙江大学   |
| 5432      | male   | 25   | 山东大学   |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai');
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing');
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang');
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong');
```

输出：

```
2138|male|21|北京大学
6543|female|20|北京大学
2315|female|23|浙江大学
5432|male|25|山东大学
```

##### 题解

`IS NULL`可用于判空，`IS NOT NULL`可用于查询非空列

```sql
SELECT
    device_id,
    gender,
    age,
    university
FROM
    user_profile
WHERE
    age IS NOT NULL
```

### 高级操作符

#### 高级操作符练习(1)

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/2d2e37474197488fbdf8f9206f66651c?tpId=199&tqId=1971781&ru=/exam/oj&qru=/ta/sql-quick-study/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要找到男性且GPA在3.5以上(不包括3.5)的用户进行调研，请你取出相关数据。

**示例：user_profile**

| id   | device_id | gender | age  | university | gpa  |
| ---- | --------- | ------ | ---- | ---------- | ---- |
| 1    | 2138      | male   | 21   | 北京大学   | 3.4  |
| 2    | 3214      | male   |      | 复旦大学   | 4.0  |
| 3    | 6543      | female | 20   | 北京大学   | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学   | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学   | 3.8  |

根据输入，你的查询应返回以下结果：

| device_id | gender | age  | university | gpa  |
| --------- | ------ | ---- | ---------- | ---- |
| 3214      | male   |      | 复旦大学   | 4.0  |
| 5432      | male   | 25   | 山东大学   | 3.8  |

示例1

输入：

```java
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong',3.8);
```

输出：

```
3214|male|None|复旦大学|4.0
5432|male|25|山东大学|3.8
```

##### 题解

根据条件查询即可

```sql
SELECT
    device_id,
    gender,
    age,
    university,
    gpa
FROM
    user_profile
WHERE
    gpa > 3.5
    AND gender = 'male'
```

#### 高级操作符练习(2）

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/25bcf6924eff417d90c8988f55675122?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要找到学校为北大或GPA在3.7以上(不包括3.7)的用户进行调研，请你取出相关数据（使用OR实现）

示例：user_profile

| id   | device_id | gender | age  | university | gpa  |
| ---- | --------- | ------ | ---- | ---------- | ---- |
| 1    | 2138      | male   | 21   | 北京大学   | 3.4  |
| 2    | 3214      | male   |      | 复旦大学   | 4.0  |
| 3    | 6543      | female | 20   | 北京大学   | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学   | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学   | 3.8  |

根据输入，你的查询应返回以下结果：

| device_id | gender | age  | university | gpa  |
| --------- | ------ | ---- | ---------- | ---- |
| 2138      | male   | 21   | 北京大学   | 3.4  |
| 3214      | male   |      | 复旦大学   | 4.0  |
| 6543      | female | 20   | 北京大学   | 3.2  |
| 5432      | male   | 25   | 山东大学   | 3.8  |

示例1

输入：

```java
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong',3.8);
```

输出：

```
2138|male|21|北京大学|3.4
3214|male|None|复旦大学|4.0
6543|female|20|北京大学|3.2
5432|male|25|山东大学|3.8
```

##### 题解

根据条件进行查询即可

```sql
SELECT
    device_id,
    gender,
    age,
    university,
    gpa
FROM
    user_profile
WHERE
    university = '北京大学'
    OR gpa > '3.7'
```

#### Where in 和Not in

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/0355033fc2244cdaa09b2bd6e794c762?tpId=199&tqId=1975665&ru=/exam/oj&qru=/ta/sql-quick-study/question-ranking&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要找到学校为北大、复旦和山大的同学进行调研，请你取出相关数据。

示例：user_profile

| id   | device_id | gender | age  | university | gpa  |
| ---- | --------- | ------ | ---- | ---------- | ---- |
| 1    | 2138      | male   | 21   | 北京大学   | 3.4  |
| 2    | 3214      | male   |      | 复旦大学   | 4.0  |
| 3    | 6543      | female | 20   | 北京大学   | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学   | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学   | 3.8  |

根据输入，你的查询应返回以下结果：

| device_id | gender | age  | university | gpa  |
| --------- | ------ | ---- | ---------- | ---- |
| 2138      | male   | 21   | 北京大学   | 3.4  |
| 3214      | male   |      | 复旦大学   | 4.0  |
| 6543      | female | 20   | 北京大学   | 3.2  |
| 5432      | male   | 25   | 山东大学   | 3.8  |

示例1

输入：

```sql
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong',3.8);
```

输出：

```
2138|male|21|北京大学|3.4
3214|male|None|复旦大学|4.0
6543|female|20|北京大学|3.2
5432|male|25|山东大学|3.8
```

##### 题解

`IN `操作符允许在 `WHERE` 子句中规定多个值。

```sql
SELECT
    device_id,
    gender,
    age,
    university,
    gpa
FROM
    user_profile
WHERE
    university IN ('北京大学', '复旦大学', '山东大学')
```

#### 操作符混合运用

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/d5ac4c878b63477fa5e5dfcb427d9102?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想要找到gpa在3.5以上(不包括3.5)的山东大学用户 或 gpa在3.8以上(不包括3.8)的复旦大学同学进行用户调研，请你取出相应数据

示例：user_profile

| id   | device_id | gender | age  | university | province | gpa  |
| ---- | --------- | ------ | ---- | ---------- | -------- | ---- |
| 1    | 2138      | male   | 21   | 北京大学   | BeiJing  | 3.4  |
| 2    | 3214      | male   | NULL | 复旦大学   | Shanghai | 4    |
| 3    | 6543      | female | 20   | 北京大学   | BeiJing  | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学   | ZheJiang | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学   | Shandong | 3.8  |

根据输入，你的查询应返回以下结果：(该题对于小数点后面的0不需要计算与统计，后台系统会统一输出小数点后面1位)

| device_id | gender | age  | university | gpa  |
| --------- | ------ | ---- | ---------- | ---- |
| 3214      | male   | NULL | 复旦大学   | 4    |
| 5432      | male   | 25   | 山东大学   | 3.8  |

示例1

输入：

```java
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`province` varchar(32)  NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学','BeiJing',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学','Shanghai',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学','BeiJing',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学','ZheJiang',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学','Shandong',3.8);
```

输出：

```
3214|male|None|复旦大学|4.0
5432|male|25|山东大学|3.8
```

##### 题解

```sql
SELECT
    device_id,
    gender,
    age,
    university,
    gpa
FROM
    user_profile
WHERE
    (
        gpa > 3.5
        AND university = '山东大学'
    )
    OR (
        gpa > 3.8
        AND university = '复旦大学'
    )
```

#### 查看学校名称中含北京的用户

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/95d9922b1e2a49de80daa491889969ee?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目：现在运营想查看所有大学中带有北京的用户的信息，请你取出相应数据。

示例：用户信息表：user_profile

| id   | device_id | gender | age  | university   | gpa  |
| ---- | --------- | ------ | ---- | ------------ | ---- |
| 1    | 2138      | male   | 21   | 北京大学     | 3.4  |
| 2    | 3214      | male   |      | 复旦大学     | 4.0  |
| 3    | 6543      | female | 20   | 北京大学     | 3.2  |
| 4    | 2315      | female | 23   | 浙江大学     | 3.6  |
| 5    | 5432      | male   | 25   | 山东大学     | 3.8  |
| 6    | 2131      | male   | 28   | 北京师范大学 | 3.3  |

根据示例，你的查询应返回如下结果：

| device_id | age  | university   |
| --------- | ---- | ------------ |
| 2138      | 21   | 北京大学     |
| 6543      | 20   | 北京大学     |
| 2131      | 28   | 北京师范大学 |

示例1

输入：

```
drop table if exists user_profile;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`gpa` float);
INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学',4.0);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8);
INSERT INTO user_profile VALUES(6,2131,'male',28,'北京师范大学',3.3);
```

输出：

```
2138|21|北京大学
6543|20|北京大学
2131|28|北京师范大学
```

##### 题解

字符匹配 一般形式为：`列名 [NOT ] LIKE`

匹配串中可包含如下四种通配符：
`_`：匹配任意一个字符；
`%`：匹配0个或多个字符；
`[ ]`：匹配[ ]中的任意一个字符(若要比较的字符是连续的，则可以用连字符“-”表 达 )；
`[^ ]`：不匹配[ ]中的任意一个字符。

```sql
SELECT
    device_id,
    age,
    university
FROM
    user_profile
WHERE
    university LIKE '%北京%'
```

## 综合练习

### 综合练习

#### 21年8月份练题总数

##### 题目描述

[原题链接](https://www.nowcoder.com/practice/b8f30b239b454ed490367b53ea95607d?tpId=199&tags=&title=&difficulty=0&judgeStatus=0&rp=0&sourceUrl=%2Fexam%2Foj%3Fpage%3D1%26tab%3DSQL%25E7%25AF%2587%26topicId%3D199)

题目： 现在运营想要了解2021年8月份所有练习过题目的总用户数和练习过题目的总次数，请取出相应结果

示例：question_practice_detail

| id   | device_id | question_id | result | date       |
| ---- | --------- | ----------- | ------ | ---------- |
| 1    | 2138      | 111         | wrong  | 2021-05-03 |
| 2    | 3214      | 112         | wrong  | 2021-05-09 |
| 3    | 3214      | 113         | wrong  | 2021-06-15 |
| 4    | 6543      | 111         | right  | 2021-08-13 |
| 5    | 2315      | 115         | right  | 2021-08-13 |
| 6    | 2315      | 116         | right  | 2021-08-14 |
| 7    | 2315      | 117         | wrong  | 2021-08-15 |
| ……   |           |             |        |            |

根据的示例，你的查询应返回以下结果：

| did_cnt | question_cnt |
| ------- | ------------ |
| 3       | 12           |

示例1

输入：

```sql
drop table if exists `user_profile`;
drop table if  exists `question_practice_detail`;
CREATE TABLE `user_profile` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`gender` varchar(14) NOT NULL,
`age` int ,
`university` varchar(32) NOT NULL,
`gpa` float,
`active_days_within_30` int ,
`question_cnt` int ,
`answer_cnt` int 
);
CREATE TABLE `question_practice_detail` (
`id` int NOT NULL,
`device_id` int NOT NULL,
`question_id`int NOT NULL,
`result` varchar(32) NOT NULL,
`date` date NOT NULL
);

INSERT INTO user_profile VALUES(1,2138,'male',21,'北京大学',3.4,7,2,12);
INSERT INTO user_profile VALUES(2,3214,'male',null,'复旦大学',4.0,15,5,25);
INSERT INTO user_profile VALUES(3,6543,'female',20,'北京大学',3.2,12,3,30);
INSERT INTO user_profile VALUES(4,2315,'female',23,'浙江大学',3.6,5,1,2);
INSERT INTO user_profile VALUES(5,5432,'male',25,'山东大学',3.8,20,15,70);
INSERT INTO user_profile VALUES(6,2131,'male',28,'山东大学',3.3,15,7,13);
INSERT INTO user_profile VALUES(7,4321,'male',28,'复旦大学',3.6,9,6,52);
INSERT INTO question_practice_detail VALUES(1,2138,111,'wrong','2021-05-03');
INSERT INTO question_practice_detail VALUES(2,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(3,3214,113,'wrong','2021-06-15');
INSERT INTO question_practice_detail VALUES(4,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(5,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(6,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(7,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(8,3214,112,'wrong','2021-05-09');
INSERT INTO question_practice_detail VALUES(9,3214,113,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(10,6543,111,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(11,2315,115,'right','2021-08-13');
INSERT INTO question_practice_detail VALUES(12,2315,116,'right','2021-08-14');
INSERT INTO question_practice_detail VALUES(13,2315,117,'wrong','2021-08-15');
INSERT INTO question_practice_detail VALUES(14,3214,112,'wrong','2021-08-16');
INSERT INTO question_practice_detail VALUES(15,3214,113,'wrong','2021-08-18');
INSERT INTO question_practice_detail VALUES(16,6543,111,'right','2021-08-13');
```

输出：

```
3|12
```

##### 题解

查询练习过题目的总用户数需要对用户进行去重，查询练习过题目的总次数不需要去重，一个题目可以被重复练习

对于日期的判断

* 使用`BETWEEN '2021-08-01' AND '2021-08-31'`

  ```sql
  SELECT
      COUNT(DISTINCT device_id) AS did_cnt,
      COUNT(question_id)
  FROM
      question_practice_detail
  WHERE
      DATE(date) BETWEEN '2021-08-01' AND '2021-08-31'
  ```

* 使用日期函数`Year()`与`Month()`

  ```sql
  SELECT
      COUNT(DISTINCT device_id) AS did_cnt,
      COUNT(question_id)
  FROM
      question_practice_detail
  WHERE
      Year (date) = 2021
      AND Month (date) = 8
  ```

* 使用`LIKE`进行匹配

  ```sql
  SELECT
      COUNT(DISTINCT device_id) AS did_cnt,
      COUNT(question_id)
  FROM
      question_practice_detail
  WHERE
      date LIKE '2021-08%'
  ```

* 使用`DATE_FORMAT()`函数对日期进行格式化

  ```sql
  SELECT
      COUNT(DISTINCT device_id) AS did_cnt,
      COUNT(question_id)
  FROM
      question_practice_detail
  WHERE
      DATE_FORMAT (date, '%Y-%m') = '2021-08'
  ```

`DATE()`函数返回日期时间的日期部分
