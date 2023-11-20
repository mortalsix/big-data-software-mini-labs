### 1. 通过MobaXterm将Spark软件压缩包传输到Ubuntu用户主目录下：


### 2. 解压Spark，在终端输入命令：
```bash
sudo tar -zxvf ~/spark-3.4.0-bin-without-hadoop.tgz -C /usr/local
```

### 3. 修改文件夹名称：
```bash
sudo mv /usr/local/spark-3.4.0-bin-without-hadoop/ /usr/local/spark
```

### 4. 更改Spark文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo chown -R user:user /usr/local/spark
```

### 5. 生成一份Spark的环境配置脚本文件
```bash
cp /usr/local/spark/conf/spark-env.sh.template /usr/local/spark/conf/spark-env.sh
```

### 6. 在MobaXterm左侧进入Spark配置文件夹/usr/local/spark/conf


### 7. 使用MobaXterm修改文件spark-env.sh

在文件第一行后添加以下内容：
```bash
export SPARK_DIST_CLASSPATH=$(/usr/local/hadoop/bin/hadoop classpath)
```
记得保存。

### 8. 启动Spark-shell
```bash
cd /usr/local/spark
bin/spark-shell
```

### 9. （如果需要）退出Spark-shell，在Spark-Shell模式下，输入
```bash
:quit
```