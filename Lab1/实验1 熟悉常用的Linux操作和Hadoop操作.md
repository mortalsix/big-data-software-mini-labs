# 实验目的
- 掌握 Linux 虚拟机的安装方法。
- 掌握一些常用的 Linux 命令。
- 掌握 Hadoop 的伪分布式安装方法。
- 掌握 Hadoop 的常用操作。

# 实验平台
- 操作系统：Windows 10
- 虚拟机软件：VirtualBox 7.0
- 终端软件：MobaXterm 23.2
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5

# 实验内容和要求
### 一、安装Linux虚拟机
##### 1. 安装并启动 VirtualBox 软件
##### 2. 在 VirtualBox 中安装 Ubuntu 系统
##### 3. 更新 apt 并安装 openssh-server
##### 4. 使用 MobaXterm 连接虚拟机

### 二、熟悉常用的Linux命令
##### 1. `cd` 切换目录
##### 2. `ls` 查看文件与目录
##### 3. `mkdir` 新建目录
##### 4. `rmdir` 删除空的目录
##### 5. `cp` 复制文件或目录
##### 6. `mv` 移动文件与目录，或重命名
##### 7. `rm` 移除文件或目录
##### 8. `cat` 查看文件内容
##### 9. `tac` 反向查看文件内容
##### 10. `more` 一页一页翻动查看文件内容
##### 11. `head` 查看文件内容的前若干行
##### 12. `tail` 查看文件内容的后若干行
##### 13. `touch` 修改文件时间或创建新文件
##### 14. `chown` 修改文件所有者
##### 15. `find` 文件查找
##### 16. `tar` 压缩命令
##### 17. `grep` 查找字符串

### 三、进行Hadoop伪分布式安装
##### 1. 使用 MobaXterm 传送 jdk 压缩包 和 Hadoop 压缩包
##### 2. 解压 JDK 压缩包 并配置 Java 环境变量
##### 3. 解压 Hadoop 压缩包 并更改 Hadoop 文件夹的用户所有权
##### 4. 修改 Hadoop 配置文件 core-site.xml 和 hdfs-site.xml

### 四、熟悉常用的Hadoop操作
##### 1. 在 HDFS 中创建用户目录 `/user/<username>`，其中 `<username>` 用实际的用户名替换
##### 2. 在上一步创建的用户目录中创建 `test` 文件夹
##### 3. 将 Linux 操作系统本地的 `~/.bashrc` 文件上传到上一步创建的 `test` 文件夹中，并查看 `test` 目录
##### 4. 将上一步 HDFS 中 `test` 目录中的 `.bashrc` 文件复制到 Linux 操作系统本地文件系统的 `/usr/local/hadoop` 目录下