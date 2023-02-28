# SQL训练

## 基础查询

### 查询所有列

#### 题目描述

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

#### 题解

对于全列查询尽量还是使用列名进行查询

```sql
SELECT id,device_id,gender,age,university,province FROM user_profile
```

### 查询多列

#### 题目描述

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

#### 题解

查询需要的列即可

```sql
SELECT device_id,gender,age,university FROM user_profile
```

### 查询结果去重

#### 题目描述

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

#### 题解

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

### 查询结果限制返回行数

#### 题目描述

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

#### 题解

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

### 将查询后的列重新命名

#### 题目描述

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

#### 题解

取别名（`AS`加不加都可以）

```sql
SELECT
    device_id AS user_infos_example
FROM
    user_profile
LIMIT
    2
```

