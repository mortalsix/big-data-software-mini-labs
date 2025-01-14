# 实验目的
- 理解 HDFS 在 Hadoop 体系结构中的角色。
- 熟练使用 HDFS 操作常用的 Shell 命令。
- 熟悉 HDFS 操作常用的 Java API。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- Java IDE：Visual Studio Code

# 实验内容和要求

### 编程实现以下指定功能，并利用 Hadoop 提供的 Shell 命令完成相同的任务
##### 1. 上传文件到 HDFS
向 HDFS 中上传任意文本文件，如果指定的文件在 HDFS 中已经存在，由用户指定是追加到原有文件末尾还是覆盖原有的文件。

Shell 命令参考：

```bash
hadoop fs -test -e {hdfs_file_path} # 测试 HDFS 文件是否存在
hadoop fs -put -f {local_file_path} {hdfs_file_path} # 上传本地文件到 HDFS 并覆盖原有文件
hadoop fs -appendToFile {local_file_path} {hdfs_file_path} # 上传本地文件到 HDFS ，追加到原有文件末尾
```

##### 2. 从 HDFS 下载文件
从 HDFS 中下载指定文件，如果本地文件与要下载的文件名称相同，则自动对下载的文件重命名。

Shell 命令参考：

```bash
if $(hadoop fs -test -e file:///home/hadoop/text.txt);
then $(hadoop fs -copyToLocal text.txt ./text2.txt); 
else $(hadoop fs -copyToLocal text.txt ./text.txt); 
fi
```

##### 3. 输出 HDFS 文件内容
将 HDFS 中指定文件的内容输出到终端。

Shell 命令参考：

```bash
hadoop fs -cat text.txt
```

##### 4. 输出 HDFS 文件详细信息
显示 HDFS 中指定的文件读写权限、大小、创建时间、路径等信息。

Shell 命令参考：

```bash
hadoop fs -ls -h text.txt
```

##### 5. 递归输出 HDFS 目录下所有文件信息
给定 HDFS 中某一个目录，输出该目录下的所有文件的读写权限、大小、创建时间、路径等信息，如果该文件是目录，则递归输出该目录下所有文件相关信息。

Shell 命令参考：

```bash
hadoop fs -ls -R -h /user/hadoop
```

##### 6. 创建、删除 HDFS 文件
提供一个 HDFS 中的文件的路径，对该文件进行创建和删除操作。如果文件所在目录不存在，则自动创建目录。

Shell 命令参考：

```bash
if $(hadoop fs -test -d dir1/dir2);
then $(hadoop fs -touchz dir1/dir2/filename); 
else $(hadoop fs -mkdir -p dir1/dir2 && hadoop fs -touchz dir1/dir2/filename); 
fi
hadoop fs -rm dir1/dir2/filename   #删除文件
```

##### 7. 创建、删除 HDFS 目录
提供一个 HDFS 的目录的路径，对该目录进行创建和删除操作。创建目录时，如果目录所在目录不存在则自动创建相应目录；删除目录时，由用户指定当该目录不为空时是否还删除该目录。

Shell 命令参考：

```bash
hadoop fs -mkdir -p dir1/dir2
hadoop fs -rmdir dir1/dir2
hadoop fs -rm -R dir1/dir2
```

##### 8. 向 HDFS 文件追加内容
向 HDFS 中指定的文件追加内容，由用户指定将内容追加到原有文件的开头或结尾。

Shell 命令参考：

```bash
hadoop fs -appendToFile local.txt text.txt
hadoop fs -get text.txt
cat text.txt >> local.txt
hadoop fs -copyFromLocal -f text.txt text.txt
```

##### 9. 删除 HDFS 文件
删除 HDFS 中指定的文件。

Shell 命令参考：

```bash
hadoop fs -rm text.txt
```

##### 10. 移动 HDFS 文件或目录
在 HDFS 中将文件从源路径移动到目的路径。

Shell 命令参考：

```bash
hadoop fs -mv text.txt text2.txt
```