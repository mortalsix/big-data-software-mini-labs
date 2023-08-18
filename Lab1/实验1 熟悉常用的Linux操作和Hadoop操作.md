# 一、实验目的
1. 掌握 Linux 虚拟机的安装方法。
2. 掌握一些常用的 Linux 命令。
3. 掌握 Hadoop 的伪分布式安装方法。
4. 掌握 Hadoop 的常用操作。

# 二、实验平台
- 操作系统：Windows 10+
- 虚拟机软件：VirtualBox 7.0
- 终端软件：MobaXterm 23.2
- JDK版本：JDK 1.8.0_162
- Hadoop版本：3.3.5

# 三、实验内容和要求
### 1. 安装Linux虚拟机
- 安装并启动 VirtualBox 软件
- 准备好 Ubuntu 镜像文件
- 在 VirtualBox 中安装 Ubuntu 系统
- 更新 apt 
- 安装 openssh-server
- 设置虚拟机网络端口转发
- 使用 MobaXterm 连接虚拟机

### 2. 熟悉常用的Linux命令
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