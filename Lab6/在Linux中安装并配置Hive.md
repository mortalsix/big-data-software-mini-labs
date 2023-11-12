### 1. 通过MobaXterm将Hive软件压缩包传输到Ubuntu用户主目录下：

### 2. 解压Hive，在终端输入命令：
```bash
sudo tar -zxvf ~/apache-hive-3.1.3-bin.tar.gz -C /usr/local
```

### 3. 修改文件夹名称：
```bash
sudo mv /usr/local/apache-hive-3.1.3-bin/ /usr/local/hive
```

### 4. 更改Hive文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo chown -R user:user /usr/local/hive
```

### 5. 使用MobaXterm修改环境变量文件 ~/.bashrc

在其最开头部分增加以下三行内容：
```bash
export HIVE_HOME=/usr/local/hive
export PATH=$PATH:$HIVE_HOME/bin
export HADOOP_HOME=/usr/local/hadoop
```

### 6. 使新的环境变量文件生效：

```bash
source ~/.bashrc
```

### 7. 启动 HDFS：

```bash
/usr/local/hadoop/sbin/start-dfs.sh
```

### 8. 启动 Hive:

```bash
cd /usr/local/hive
bin/hive
```

### 错误处理：如果遇到“Hive metastore database is not initialized”的错误，执行以下命令后再启动hive

```bash
cd /usr/local/hive
./bin/schematool -dbType derby -initSchema
```