# 实验目的
- 掌握 Linux 虚拟机的安装方法。
- 掌握一些常用的 Linux 命令。
- 掌握 Hadoop 的伪分布式安装方法。
- 掌握 Hadoop 的常用操作。

# 实验平台
- 操作系统：Windows 10
- 虚拟机软件：VirtualBox 7.0
- 终端软件：MobaXterm 23.2
- JDK版本：JDK 1.8.0_162
- Hadoop版本：3.3.5

# 实验内容和要求
### 一、安装Linux虚拟机
1. 安装并启动 VirtualBox 软件
1. 准备好 Ubuntu 镜像文件
1. 在 VirtualBox 中安装 Ubuntu 系统
1. 更新 apt 
1. 安装 openssh-server
1. 设置虚拟机网络端口转发
1. 使用 MobaXterm 连接虚拟机

### 二、熟悉常用的Linux命令
1. `cd` 切换目录
1. `ls` 查看文件与目录
1. `mkdir` 新建目录
1. `rmdir` 删除空的目录
1. `cp` 复制文件或目录
1. `mv` 移动文件与目录，或重命名
1. `rm` 移除文件或目录
1. `cat` 查看文件内容
1. `tac` 反向查看文件内容
1. `more` 一页一页翻动查看文件内容
1. `head` 查看文件内容的前若干行
1. `tail` 查看文件内容的后若干行
1. `touch` 修改文件时间或创建新文件
1. `chown` 修改文件所有者
1. `find` 文件查找
1. `tar` 压缩命令
1. `grep` 查找字符串

### 三、进行Hadoop伪分布式安装
1. 使用 MobaXterm 传送 jdk 压缩包 和 Hadoop 压缩包
1. 解压 JDK 压缩包
1. 配置 Java 环境变量
1. 解压 Hadoop 压缩包
1. 重命名 Hadoop 文件夹，更改 Hadoop 文件夹所有者
1. 修改 Hadoop 配置文件 core-site.xml 和 hdfs-site.xml

### 四、熟悉常用的Hadoop操作
1. 在 HDFS 中创建用户目录 `/user/<username>`，其中 `<username>` 用实际的用户名替换
1. 在上一步创建的用户目录中创建 `test` 文件夹
1. 将 Linux 操作系统本地的 `~/.bashrc` 文件上传到上一步创建的 `test` 文件夹中，并查看 `test` 目录
1. 将上一步 HDFS 中 `test` 目录中的 `.bashrc` 文件复制到 Linux 操作系统本地文件系统的 `/usr/local/hadoop` 目录下