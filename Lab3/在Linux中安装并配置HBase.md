### 1. 通过MobaXterm将HBase软件压缩包传输到Ubuntu用户主目录下：
![Alt text](img/%E4%B8%8A%E4%BC%A0HBase%E5%88%B0Ubuntu%E7%94%A8%E6%88%B7%E4%B8%BB%E7%9B%AE%E5%BD%95.png)

### 2. 解压HBase，在终端输入命令：
```bash
sudo tar -zxvf ~/hbase-2.5.4-bin.tar.gz -C /usr/local
```

### 3. 修改文件夹名称：
```bash
sudo mv /usr/local/hbase-2.5.4/ /usr/local/hbase
```

### 4. 更改HBase文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo chown -R user:user /usr/local/hbase
```

### 5. 在MobaXterm左侧进入HBase配置文件夹/usr/local/hbase/conf
![Alt text](img/%E5%9C%A8MobaXterm%E4%B8%AD%E8%BF%9B%E5%85%A5HBase%E9%85%8D%E7%BD%AE%E6%96%87%E4%BB%B6%E5%A4%B9.png)

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
