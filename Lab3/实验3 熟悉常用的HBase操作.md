# 实验目的
- 理解 HBase 在 Hadoop 体系结构中的角色。
- 熟练使用 HBase 操作常用的 Shell 命令。
- 熟悉 HBase 操作常用的 Java API。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- HBase 版本：2.5.4
- Java IDE：Visual Studio Code

# 实验内容和要求
### 一、编程实现以下指定功能，并用 Hadoop 提供的 HBase Shell 命令完成相同的任务。
##### 1. 列出 HBase 所有表的相关信息，如表名、创建时间等。
##### 2. 在终端输出指定表的所有记录数据。
##### 3. 向已经创建好的表添加和删除指定的列族或列。
##### 4. 清空指定表的所有记录数据。
##### 5. 统计表的行数。

### 二、现有以下关系数据库中的表（见表1、表2和表3），完成指定任务。

> 表 1 学生（Student）表 

|学号（S_No）|姓名（S_Name）|性别（S_Sex）|年龄（S_Age）|
| ---- | ---- | ---- | ---- |
| 2015001 | Zhangsan | male | 23 |
| 2015002 | Mary | female | 22 |
| 2015003 | Lisi | male | 24 |


> 表 2 课程（Course）表 

|课程号（C_No）|课程名（C_Name）|学号（C_Credit）|
| ---- | ---- | ---- |
| 123001 | Math | 2.0 |
| 123002 | Computer Science | 5.0 |
| 123003 | English | 3.0 |


> 表 3 选课（SC）表 

|学号（SC_No）|课程号（SC_No）|成绩（SC_Score）|
| ---- | ---- | ---- |
| 2015001 | 123001 | 86 |
| 2015001 | 123003 | 69 |
| 2015002 | 123002 | 77 |
| 2015002 | 123003 | 99 |
| 2015003 | 123001 | 98 |
| 2015003 | 123002 | 95 |

##### 6. 创建表并插入数据

将表1、表2和表3中的数据转换为适合 HBase 存储的表，并使用 HBase Shell 命令插入数据。

##### 7. 编程实现功能 `createTable(String tableName, String[] fields)` 

功能描述：创建表，参数 tableName 为表的名称，字符串数组 fields 为存储记录各个域名称的数组。要求当 HBase 已经存在名为 tableName 的表的时候，先删除原有的表，再创建新的表。

##### 8. 编程实现功能 `addRecord(String tableName, String row, String[] fields, String[] values)` 

功能描述：向表 tableName、行 row（用 S_Name 表示）和字符串数组fields指定的单元格中添加对应的数据 values。其中，如果fields中每个元素对应的列族下还有相应的列限定符， 用 columnFamily:column 表示。 例如同时向 Math、 Computer Science、 English 3列添加成绩时， 字符串数组 fields 为 {"Score:Math", "Score:Computer Science", "Score:English"}， 数组 values 存储这3门课的成绩。

##### 9. 编程实现功能 `scanColumn(String tableName, String column)`  

功能描述：浏览表 tableName 某一列的数据， 如果某一行记录中该列数据不存在， 则返回 null 。 要求当参数  column 为某一列族名称时， 如果底下有若干个列限定符， 则列出每个列限定符代表的列的数据； 当参数 column 为某一列具体名称（ 如 Score:Math ） 时， 只需要列出该列的数据。

##### 10. 编程实现功能 `modifyData(String tableName, String row, String column)` 

功能描述：修改表 tableName、 行 row （可以用学生姓名 S_Name 表示） 、 列 column 指定的单元格的数据。

##### 11. 编程实现功能 `deleteRow(String tableName, String row)` 

功能描述：删除表 tableName 中 row 指定的行的记录。
