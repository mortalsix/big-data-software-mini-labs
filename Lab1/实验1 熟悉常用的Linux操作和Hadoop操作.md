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
##### 4. 使用 MobaXterm 连接 Ubuntu 虚拟机

### 二、熟悉常用的Linux命令
##### 5. `cd` 切换目录
* 切换到目录 /usr/local 。
* 切换到当前目录的上一级目录。
* 切换到当前登录 Linux 操作系统的用户的自己的主文件夹。

##### 6. `ls` 查看文件与目录
* 查看目录 /usr 下的所有文件和目录。

##### 7. `mkdir` 新建目录
* 进入 /tmp 目录，创建一个名为 a 的目录，并查看 /tmp 目录下已经存在哪些目录。
* 进入 /tmp 目录，创建目录 a1/a2/a3/a4 。

##### 8. `rmdir` 删除空的目录
* 将上面创建的目录 a （在 /tmp 目录下面）删除。
* 删除上面创建的目录 a1/a2/a3/a4 （在 /tmp 目录下面），然后查看 /tmp 目录下面存在哪些目录。

##### 9. `cp` 复制文件或目录
* 将当前用户的主文件夹下的文件 .bashrc 复制到目录 /usr 下，并重命名为 bashrc1 。
* 在目录 /tmp 下新建目录 test ，再把这个目录复制到 /usr 目录下。

##### 10. `mv` 移动文件与目录，或重命名
* 将 /usr 目录下的文件 bashrc1 移动到 /usr/test 目录下。
* 将 /usr 目录下的 test 目录重命名为 test2 。

##### 11. `rm` 移除文件或目录
* 将 /usr/test2 目录下的 bashrc1 文件删除。
* 将 /usr 目录下的 test2 目录删除。

##### 12. `cat` 查看文件内容
* 查看当前用户主文件夹下的 .bashrc 文件内容。

##### 13. `tac` 反向查看文件内容
* 反向查看当前用户主文件夹下的 .bashrc 文件内容。

##### 14. `more` 一页一页翻动查看文件内容
* 翻页查当前用户主文件夹下的 .bashrc 文件内容。

##### 15. `head` 查看文件内容的前若干行
* 查看当前用户主文件夹下的 .bashrc 文件内容前 20 行。
* 查看当前用户主文件夹下的 .bashrc 文件内容，后面 50 行不显示，只显示前面几行。

##### 16. `tail` 查看文件内容的后若干行
* 查看当前用户主文件夹下的 .bashrc 文件内容最后 20 行。
* 查看当前用户主文件夹下的 .bashrc 文件内容，并且只列出 50 行以后的内容。

##### 17. `touch` 修改文件时间或创建新文件
* 在 /tmp 目录下创建一个空文件 hello ，并查看文件时间。
* 修改 hello 文件，将文件时间修改为 5 天前。

##### 18. `chown` 修改文件所有者
* 将 hello 文件所有者改为 root ，并查看属性。

##### 19. `find` 文件查找
* 找出当前用户主文件夹下文件名为 .bashrc 的文件。

##### 20. `tar` 压缩命令
* 在根目录 / 下新建文件夹 test ，然后再根目录  / 下打包成 test.tar.gz 。
* 把上面的 test.tar.gz 压缩包解压缩到 /tmp 目录。

##### 21. `grep` 查找字符串
* 从 ~/.bashrc 文件中查找字符串 examples 。

##### 22. 查看环境变量
* 使用 echo 命令查看环境变量 PATH 的值。

### 三、进行Hadoop伪分布式安装
##### 23. 使用 MobaXterm 传送 jdk 压缩包 和 Hadoop 压缩包
##### 24. 解压 JDK 压缩包 并配置 Java 环境变量

(1) 解压
```shell
sudo mkdir /usr/lib/jvm
sudo tar -zxf jdk-8u162-linux-x64.tar.gz -C /usr/lib/jvm
```
(2) 修改环境变量，在~/.bashrc文件的开头位置添加如下两行文本：

```shell
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$PATH:$JAVA_HOME/bin
```
(3）让环境变量生效，并检查是否生效

```shell
source ~/.bashrc
java -version
```



##### 25. 解压 Hadoop 压缩包 并更改 Hadoop 文件夹的用户所有权

```shell
sudo tar -zxvf hadoop-3.3.5.tar.gz -C /usr/local
sudo mv /usr/local/hadoop-3.3.5 /usr/local/hadoop
```
以下代码中的用户名要替换成自己的
```shell
sudo chown -R you:you /usr/local/hadoop
```



##### 26. 修改 Hadoop 配置文件 core-site.xml 和 hdfs-site.xml

### 四、熟悉常用的Hadoop操作
##### 27. 创建 HDFS 用户目录
在 HDFS 中创建用户目录 /user/<username>，其中 <username> 用实际的用户名替换。

##### 28. 将本地文件上传到 HDFS
在上一步创建的用户目录中创建 test 文件夹，并将 Linux 操作系统本地的 ~/.bashrc 文件上传到上一步创建的 test 文件夹中，并查看 HDFS 中的 test 目录。

##### 29. 从 HDFS 复制文件到本地
将上一步 HDFS 中 test 目录中的 .bashrc 文件复制到 Linux 操作系统本地文件系统的 /usr/local/hadoop 目录下