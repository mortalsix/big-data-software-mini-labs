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

### 5. 启动Flink服务
```bash
cd /usr/local/flink
./bin/start-cluster.sh
```
