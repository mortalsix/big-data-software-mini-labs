### 1. 通过MobaXterm将sbt软件压缩包传输到Ubuntu用户主目录下


### 2. 解压sbt，在终端输入命令：
```bash
sudo tar -zxvf ~/sbt-1.9.2.tgz -C /usr/local
```

### 3. 更改sbt文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo chown -R user:user /usr/local/sbt
```

### 4. 把bin目录下的sbt-launch.jar复制到sbt安装目录下
```bash
cd /usr/local/sbt
cp ./bin/sbt-launch.jar ./
```

### 5. 创建用来启动sbt的Shell脚本文件 `sbt`
```bash
touch /usr/local/sbt/sbt
```

### 6. 使用MobaXterm或vim向`sbt`文件中添加以下内容：
```
#!/bin/bash
SBT_OPTS="-Xms512M -Xmx1536M -Xss1M -XX:+CMSClassUnloadingEnabled -XX:MaxPermSize=256M"
java $SBT_OPTS -jar `dirname $0`/sbt-launch.jar "$@"
```

### 7. 为脚本文件增加可执行权限
```bash
chmod u+x /usr/local/sbt/sbt
```

### 8. 更换仓库地址，加快编译速度，创建`repositories`文件
```bash
cd ~
mkdir .sbt   # 如果该目录已经存在，就不用创建了
cd .sbt
touch repositories # 创建repositories文件
```

### 9. 在文件`repositories`中添加如下内容并保存：
```
[repositories]
local
huaweicloud-maven: https://repo.huaweicloud.com/repository/maven/
maven-central: https://repo1.maven.org/maven2/
sbt-plugin-repo: https://repo.scala-sbt.org/scalasbt/sbt-plugin-releases, [organization]/[module]/(scala_[scalaVersion]/)(sbt_[sbtVersion]/)[revision]/[type]s/[artifact](-[classifier]).[ext]
```

### 10. 修改sbt配置文件`/usr/local/sbt/conf/sbtopts`，在其末尾增加一行：
```
-Dsbt.override.build.repos=true
```

### 11. 初始化sbt并打印sbt版本信息，在终端输入：
```bash
cd /usr/local/sbt
./sbt sbtVersion
```

此过程要经历一段时间，顺利的话，等待数分钟后，就可以看到终端输出了 sbt 版本号，此时表明sbt初始化完成，可以进行后续的scala程序编译打包任务了