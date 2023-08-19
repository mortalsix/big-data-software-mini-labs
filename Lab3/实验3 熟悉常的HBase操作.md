# 一、实验目的
1. 理解 HBase 在 Hadoop 体系结构中的角色。
2. 熟练使用 HBase 操作常用的 Shell 命令。
4. 熟悉 HBase 操作常用的 Java API。

# 二、实验平台
- 操作系统：Ubuntu 22.04
- JDK版本：JDK 1.8.0_162
- Hadoop版本：3.3.5
- HBase版本：2.5.4
- Java IDE：Visual Studio Code

# 三、实验内容和要求
### 1. 编程实现以下指定功能，并用 Hadoop 提供的 HBase Shell 命令完成相同的任务。
- 列出 HBase 所有表的相关信息，如表名、创建时间等。
- 在终端输出指定表的所有记录数据。
- 向已经创建好的表添加和删除指定的列族或列。
- 清空指定表的所有记录数据。
- 统计表的行数。

### 2. 现有以下关系数据库中的表（见表1、表2和表3），要求将其转换为适合 HBase 存储的表并插入数据。同时，编程完成以下指定功能。

表1  <center> 学生（Student）表 </center>
|   学号（S_No）   |   姓名（S_Name）  |  性别（S_Sex）  |
| ---- | ---- | ---- |
|      |      |      |
|      |      |      |
|      |      |      |


- `cd` 切换目录
- `ls` 查看文件与目录
- `mkdir` 新建目录
- `rmdir` 删除空的目录
- `cp` 复制文件或目录
- `mv` 移动文件与目录，或重命名
- `rm` 移除文件或目录
- `cat` 查看文件内容
- `tac` 反向查看文件内容
- `more` 一页一页翻动查看文件内容
- `head` 查看文件内容的前若干行
- `tail` 查看文件内容的后若干行
- `touch` 修改文件时间或创建新文件
- `chown` 修改文件所有者
- `find` 文件查找
- `tar` 压缩命令
- `grep` 查找字符串

### 3. 进行Hadoop伪分布式安装
- 使用 MobaXterm 传送 jdk 压缩包 和 Hadoop 压缩包
- 解压 JDK 压缩包
- 配置 Java 环境变量
- 解压 Hadoop 压缩包
- 重命名 Hadoop 文件夹，更改 Hadoop 文件夹所有者
- 修改 Hadoop 配置文件 core-site.xml 和 hdfs-site.xml

### 4. 熟悉常用的Hadoop操作
- 在 HDFS 中创建用户目录 `/user/<username>`，其中 `<username>` 用实际的用户名替换
- 在上一步创建的用户目录中创建 `test` 文件夹
- 将 Linux 操作系统本地的 `~/.bashrc` 文件上传到上一步创建的 `test` 文件夹中，并查看 `test` 目录
- 将上一步 HDFS 中 `test` 目录中的 `.bashrc` 文件复制到 Linux 操作系统本地文件系统的 `/usr/local/hadoop` 目录下