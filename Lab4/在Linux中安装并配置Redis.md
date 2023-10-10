### 1. 执行以下 Shell 命令：
```bash
sudo apt install -y redis-server

```
如果在终端出现交互界面，要求你设置root密码，请输入两次一样的密码，随后安装过程就会结束

### 2. 查看 mysql 服务是否启动成功
```bash
sudo systemctl status redis.service

```
如果出现类似下面的结果，说明 mysql 服务启动成功。

![Alt text](img/redis%E6%9C%8D%E5%8A%A1%E5%90%AF%E5%8A%A8%E6%88%90%E5%8A%9F.png)

### 3. 打开 Redis Shell 进行操作
```bash
redis-cli
```
