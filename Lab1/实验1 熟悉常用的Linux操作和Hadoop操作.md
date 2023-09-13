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

![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC01%E6%AD%A5.png)
![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC02%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC03%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC04%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC05%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC06%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC07%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC08%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC09%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC10%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC11%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC12%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC13%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC14%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC15%E6%AD%A5.png)
![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC16%E6%AD%A5.png) ![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC17%E6%AD%A5.png)
![Alt text](%E5%AE%89%E8%A3%85Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA%E7%AC%AC18%E6%AD%A5.png)

##### 3. 更新 apt 并安装 openssh-server
登录系统后，更新包管理器 apt，打开终端，在其中敲下如下命令：
```bash
sudo apt update
```
更新成功如下图所示：
![Alt text](%E6%9B%B4%E6%96%B0apt.png)

然后执行以下命令安装 openssh-server：
```bash
sudo apt install -y openssh-server
```

##### 4. 使用 MobaXterm 连接 Ubuntu 虚拟机

先设置虚拟机网络端口转发，操作步骤如下：

![Alt text](VirtualBox%E8%AE%BE%E7%BD%AE%E7%BD%91%E7%BB%9C%E7%AB%AF%E5%8F%A3%E8%BD%AC%E5%8F%91%E7%AC%AC1%E6%AD%A5.png) 
![Alt text](VirtualBox%E8%AE%BE%E7%BD%AE%E7%BD%91%E7%BB%9C%E7%AB%AF%E5%8F%A3%E8%BD%AC%E5%8F%91%E7%AC%AC2%E6%AD%A5.png)

然后使用 MobaXterm 连接 Ubuntu，操作步骤如下：

![Alt text](MobaXterm%E8%BF%9E%E6%8E%A5Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA.png)

提示你输入密码，说明连接成功，输入你的密码，即可实现登录，登录成功后的界面大概是这样：

![Alt text](MobaXterm%E8%BF%9E%E6%8E%A5Ubuntu%E8%99%9A%E6%8B%9F%E6%9C%BA-%E7%99%BB%E5%BD%95%E6%88%90%E5%8A%9F.png)

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
```bash
sudo mkdir /usr/lib/jvm
sudo tar -zxf ~/jdk-8u162-linux-x64.tar.gz -C /usr/lib/jvm
```
(2) 【此步骤为修改文件，不要直接在终端输入】修改环境变量

使用 MobaXterm 自带的编辑器打开环境变量文件：

![Alt text](%E4%BD%BF%E7%94%A8MobaXterm%E4%BF%AE%E6%94%B9%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F%E6%96%87%E4%BB%B6.png)

在 .bashrc 文件的开头位置添加以下两行内容：

```bash
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export PATH=$PATH:$JAVA_HOME/bin
```

修改后的 .bashrc 文件大概是这样：

![Alt text](%E4%BD%BF%E7%94%A8MobaXterm%E4%BF%AE%E6%94%B9%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F%E6%96%87%E4%BB%B6-2.png)

然后，点击保存按钮，并在弹出的对话框中点击“yes”，完成 Ubuntu 环境变量文件的修改。

![Alt text](%E4%BD%BF%E7%94%A8MobaXterm%E4%BF%AE%E6%94%B9%E7%8E%AF%E5%A2%83%E5%8F%98%E9%87%8F%E6%96%87%E4%BB%B6-3.png)

(3）让环境变量生效，并检查是否生效
```bash
source ~/.bashrc
java -version
```


##### 25. 解压 Hadoop 压缩包 并更改 Hadoop 文件夹的用户所有权

```bash
sudo tar -zxvf ~/hadoop-3.3.5.tar.gz -C /usr/local
sudo mv /usr/local/hadoop-3.3.5 /usr/local/hadoop
```
以下代码中的 `you` 要替换成自己的用户名
```bash
sudo chown -R you:you /usr/local/hadoop
```



##### 26. 修改 Hadoop 配置文件 `core-site.xml` 和 `hdfs-site.xml`

(1) 将 `core-site.xml` 文件的配置项修改如下：

```xml
<configuration>
    <property>
        <name>hadoop.tmp.dir</name>
        <value>file:/usr/local/hadoop/tmp</value>
        <description>Abase for other temporary directories.</description>
    </property>
    <property>
        <name>fs.defaultFS</name>
        <value>hdfs://localhost:9000</value>
    </property>
</configuration>
```

(2) 将 `hdfs-site.xml` 文件的配置项修改如下：

```xml
<configuration>
    <property>
        <name>dfs.replication</name>
        <value>1</value>
    </property>
    <property>
        <name>dfs.namenode.name.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/name</value>
    </property>
    <property>
        <name>dfs.datanode.data.dir</name>
        <value>file:/usr/local/hadoop/tmp/dfs/data</value>
    </property>
</configuration>
```

##### 27. 配置ssh无密码登录

(1) ssh连接本机
```bash
ssh localhost
```
提示输入密码，登录后，退出
```bash
exit
```
(2) 生成密钥对

```bash
ssh-keygen -t rsa -P '' -f ~/.ssh/id_rsa
```

(3) 分发密钥对

```bash
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys
chmod 0600 ~/.ssh/authorized_keys
```

(4) 验证是否成功
```bash
ssh localhost
```
发现不用输入密码，登录后，退出
```bash
exit
```

##### 28. 初始化 Hadoop 文件系统 HDFS，启动 HDFS 相关服务

(1) 初始化
```bash
/usr/local/hadoop/bin/hdfs namenode -format
```

(2) 启动 HDFS 服务
```bash
/usr/local/hadoop/sbin/start-dfs.sh
```

(3) 检查是否启动成功
```bash
jps
```
如果显示出来的 Java 进程包括 `NameNode`、`DataNode`、`SecondaryNameNode`，说明启动成功；反之说明启动失败。


### 四、熟悉常用的Hadoop操作
##### 29. 创建 HDFS 用户目录
在 HDFS 中创建用户目录 /user/<username>，其中 <username> 用实际的用户名替换。

##### 30. 将本地文件上传到 HDFS
在上一步创建的用户目录中创建 test 文件夹，并将 Linux 操作系统本地的 ~/.bashrc 文件上传到上一步创建的 test 文件夹中，并查看 HDFS 中的 test 目录。

##### 31. 从 HDFS 复制文件到本地
将上一步 HDFS 中 test 目录中的 .bashrc 文件复制到 Linux 操作系统本地文件系统的 /usr/local/hadoop 目录下