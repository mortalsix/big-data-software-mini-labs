### 1. 通过MobaXterm将Flink软件压缩包传输到Ubuntu用户主目录下

### 2. 解压Flink，在终端输入命令：
```bash
sudo tar -zxvf flink-1.16.2-bin-scala_2.12.tgz -C /usr/local
```

### 3. 修改文件夹名称：
```bash
sudo mv /usr/local/flink-1.16.2/ /usr/local/flink
```

### 4. 更改Flink文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo chown -R user:user /usr/local/flink
```

### 5. 在MobaXterm左侧进入HBase配置文件夹/usr/local/hbase/conf

### 6. 使用MobaXterm修改文件hbase-env.sh
![Alt text](img/%E9%85%8D%E7%BD%AEhbase-env.sh%E6%96%87%E4%BB%B6.png)

添加的内容为：
```bash
export JAVA_HOME=/usr/lib/jvm/jdk1.8.0_162
export HBASE_MANAGES_ZK=true 
```
记得保存。

### 7. 使用MobaXterm修改文件hbase-site.xml
![Alt text](img/%E9%85%8D%E7%BD%AEhbase-site.xml%E6%96%87%E4%BB%B6.png)

添加的内容为：
```xml
  <property>
    <name>hbase.rootdir</name>
    <value>file:///usr/local/hbase/hbase-tmp</value>
  </property>
```
记得保存。

### 8. 启动HBase
```bash
cd /usr/local/hbase
bin/start-hbase.sh
```

### 9. 查看是否启动成功
```bash
jps
```
如果发现有 `HMaster` 说明启动成功。


### 10. 打开 HBase Shell 进行操作
```bash
cd /usr/local/hbase
bin/hbase shell
```
![Alt text](img/%E6%89%93%E5%BC%80HBaseShell.png)