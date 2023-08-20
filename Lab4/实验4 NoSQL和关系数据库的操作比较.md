# 实验目的
- 理解 4 种数据库（ MySQL、HBase、Redis 和 MongoDB ）的概念以及不同点。
- 熟练使用 4 种数据库操作常用的 Shell 命令。
- 熟悉 4 种数据库操作常用的 Java API。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- MySQL 版本：5.6
- Redis 版本：7.2
- MongoDB 版本：7.0
- Java IDE：Visual Studio Code

# 实验内容和要求
### 一、MySQL 数据库操作

> 表 1 学生（Student）表 

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| zhangsan | 69 | 86 | 77 |
| lisi | 55 | 100 | 88 |

##### 1. 创建 MySQL 表
    根据表 1 ，在 MySQL 中创建 Student 表，并录入数据。

##### 2. 输出 MySQL 表全部数据
    用 SQL 语句输出 Student 表中的所有记录。

##### 3. 查询 MySQL 表数据
    查询 zhangsan 的 Computer 成绩。

##### 4. 修改 MySQL 表数据
    修改 lisi 的 Math 成绩为 95。

##### 5. 使用 Java 编程向 MySQL 表添加数据
    向 Student 表 添加如下所示的一条数据：

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| scofield | 45 | 89 | 100 |

##### 6. 使用 Java 编程查询 MySQL 表数据
    从 Student 表获取 wangwu 的 English 成绩。

### 二、HBase 数据库操作

> 表 2 学生（Student）表 

|Name|Score:English|Score:Math|Score:Computer|
| ---- | ---- | ---- | ---- |
| zhangsan | 69 | 86 | 77 |
| lisi | 55 | 100 | 88 |

##### 1. 创建 HBase 表
    根据表 2 ，用 HBase Shell 命令创建学生（Student）表。

##### 2. 输出 HBase 表元数据
    用 scan 命令浏览 Student 表的相关信息。

##### 3. 查询 HBase 表数据
    查询 zhangsan 的 Computer 成绩。

##### 4. 修改 HBase 表数据
    修改 lisi 的 Math 成绩为 95。

##### 5. 使用 Java 编程向 HBase 添加数据
    向 Student 表中添加如下所示的一条记录：

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| scofield | 45 | 89 | 100 |

##### 6. 使用 Java 编程查询 HBase 表数据
    从 Student 表获取 scofield 的 English 成绩信息。

### 三、Redis 数据库操作

> Student 键值对如下：
```js
zhangsan: {
    English: 69
    Math: 86
    Computer: 77
}
lisi: {
    English: 55
    Math: 100
    Computer: 88
}
```

##### 1. 创建 Redis 键值结构
    用 Redis 的哈希结构设计出 Student 表（键值可以用 student.zhangsan 和 student.lisi 来表示两个键值属于同一个表）。

##### 2. 输出 Redis 键值数据
    用 hgetall 命令分别输出 zhangsan 和 lisi 的成绩信息。

##### 3. 查询 Redis 键值数据
    用 hget 命令查询 zhangsan 的 Computer 成绩。

##### 4. 修改 Redis 键值数据
    修改 lisi 的 Math 成绩为 95。

##### 5. 使用 Java 编程向 Redis 添加数据
    向 Student 表中添加如下所示的一条记录。该数据对应的键值对形式如下：

```js
scofield: {
    English: 45
    Math: 89
    Computer: 100
}
```

##### 6. 使用 Java 编程查询 Redis 键值数据
    获取 scofield 的 English 成绩信息。

### 四、MongoDB 数据库操作

> Student 文档如下：
```js
{
    "name": "zhangsan",
    "score": {
        "English": 69,
        "Math": 86,
        "Computer": 77
    }
}
{
    "name": "lisi",
    "score": {
        "English": 55,
        "Math": 100,
        "Computer": 88
    }
}
```

##### 1. 创建 MongoDB 集合
    用 MongoDB Shell 设计出 Student 集合。

##### 2. 输出 MongoDB 集合数据
    用 find() 方法输出两个学生的信息。

##### 3. 查询 MongoDB 文档数据
    用 find() 方法查询 zhangsan 的所有成绩（只显示 score 列）。

##### 4. 修改 MongoDB 文档数据
    修改 lisi 的 Math 成绩为 95。

##### 5. 使用 Java 编程向 MongoDB 添加记录
    向 Student 表中添加如下所示的一条记录。该数据对应的文档形式如下：

```js
{
    "name": "scofield",
    "score": {
        "English": 45,
        "Math": 89,
        "Computer": 100
    }
}
```

##### 6. 使用 Java 编程查询 MongoDB 文档数据
    获取 scofield 的 English 成绩信息（只显示 score 列）。