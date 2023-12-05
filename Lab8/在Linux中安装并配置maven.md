### 0. 下载 maven ： https://dlcdn.apache.org/maven/maven-3/3.9.6/binaries/apache-maven-3.9.6-bin.tar.gz

### 1. 通过MobaXterm将maven软件压缩包传输到Ubuntu用户主目录下


### 2. 解压maven，在终端输入命令：
```bash
sudo tar -zxvf ~/apache-maven-3.9.6-bin.tar.gz -C /usr/local
```

### 3. 重命名并更改maven文件夹权限（注意，修改以下命令中的user为自己Ubuntu的用户名）
```bash
sudo mv /usr/local/apache-maven-3.9.6/ /usr/local/maven/
sudo chown -R user:user /usr/local/maven
```

### 4. 更换仓库地址，加快下载依赖包的速度，使用趁手的文本编辑器修改maven软件的settings.xml文件，将其中的全部内容删掉替换为如下内容
```xml
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                      http://maven.apache.org/xsd/settings-1.0.0.xsd">
<mirrors>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云公共仓库</name>
        <url>https://maven.aliyun.com/repository/public</url>
    </mirror>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云谷歌仓库</name>
        <url>https://maven.aliyun.com/repository/google</url>
    </mirror>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云阿帕奇仓库</name>
        <url>https://maven.aliyun.com/repository/apache-snapshots</url>
    </mirror>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云spring仓库</name>
        <url>https://maven.aliyun.com/repository/spring</url>
    </mirror>
    <mirror>
        <id>aliyunmaven</id>
        <mirrorOf>*</mirrorOf>
        <name>阿里云spring插件仓库</name>
        <url>https://maven.aliyun.com/repository/spring-plugin</url>
    </mirror>
</mirrors>
</settings>
```