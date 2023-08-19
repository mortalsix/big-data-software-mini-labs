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

#### 表 1 学生（Student）表 

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| zhangsan | 69 | 86 | 77 |
| lisi | 55 | 100 | 88 |

1. 根据表 1 ，在 MySQL 中创建 Student 表，并录入数据。
1. 用 SQL 语句输出 Student 表中的所有记录。
1. 查询 zhangsan 的 Computer 成绩。
1. 修改 lisi 的 Math 成绩为 95。
### 二、HBase 数据库操作

#### 表 1 学生（Student）表 

|学号（S_No）|姓名（S_Name）|性别（S_Sex）|年龄（S_Age）|
| ---- | ---- | ---- | ---- |
| 2015001 | Zhangsan | male | 23 |
| 2015002 | Mary | female | 22 |
| 2015003 | Lisi | male | 24 |


1. 将表1、表2和表3中的数据转换为适合 HBase 存储的表，并使用 HBase Shell 命令插入数据。

1. 编程实现功能 `createTable(String tableName, String[] fields)` 

    功能描述：创建表，参数 `tableName` 为表的名称，字符串数组 `fields` 为存储记录各个域名称的数组。要求当 HBase 已经存在名为 `tableName` 的表的时候，先删除原有的表，再创建新的表。

1. 编程实现功能 `addRecord(String tableName, String row, String[] fields, String[] values)` 
    
    功能描述：向表 `tableName`、行 `row`（用 `S_Name` 表示）和字符串数组fields指定的单元格中添加对应的数据 `values`。其中，如果fields中每个元素对应的列族下还有相应的列限定符， 用 `columnFamily:column` 表示。 例如同时向 `Math`、 `Computer Science`、 `English` 3列添加成绩时， 字符串数组 `fields` 为 `{"Score:Math", "Score:Computer Science", "Score:English"}`， 数组 `values` 存储这3门课的成绩。

1. 编程实现功能 `scanColumn(String tableName, String column)`  
    
    功能描述：浏览表 `tableName` 某一列的数据， 如果某一行记录中该列数据不存在， 则返回 `null` 。 要求当参数  `column` 为某一列族名称时， 如果底下有若干个列限定符， 则列出每个列限定符代表的列的数据； 当参数 `column` 为某一列具体名称（ 如 `Score:Math` ） 时， 只需要列出该列的数据。

1. 编程实现功能 `modifyData(String tableName, String row, String column)` 
    
    功能描述：修改表 `tableName`、 行 `row` （可以用学生姓名 `S_Name` 表示） 、 列 `column` 指定的单元格的数据。

1. 编程实现功能 `deleteRow(String tableName, String row)` 

    功能描述：删除表 `tableName` 中 `row` 指定的行的记录。

### 三、Redis 数据库操作

### 四、MongoDB 数据库操作