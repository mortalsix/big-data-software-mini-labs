### 1. 执行以下 Shell 命令：
```bash
sudo apt install -y mysql-server

```
如果在终端出现交互界面，要求你设置root密码，请输入两次一样的密码，随后安装过程就会结束

### 2. 查看 mysql 服务是否启动成功
```bash
sudo systemctl status mysql.service

```
如果出现类似下面的结果，说明 mysql 服务启动成功。

![Alt text](img/mysql%E6%9C%8D%E5%8A%A1%E5%90%AF%E5%8A%A8%E6%88%90%E5%8A%9F.png)

### 3. 打开 MySQL Shell 进行操作
```bash
mysql -u root -p密码  # 注意这里-p和密码之间没有空格
```
会出现以下界面：

![Alt text](img/%E6%89%93%E5%BC%80MySQLShell%E8%BF%9B%E8%A1%8C%E6%93%8D%E4%BD%9C.png)