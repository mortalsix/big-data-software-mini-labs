# 实验目的
- 理解 4 种数据库（ MySQL、HBase、Redis 和 MongoDB ）的概念以及不同点。
- 熟练使用 4 种数据库操作常用的 Shell 命令。
- 熟悉 4 种数据库操作常用的 Java API。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- MySQL 版本：5.7
- Redis 版本：7.2
- MongoDB 版本：7.0
- Java IDE：Visual Studio Code

# 实验内容和要求
## 一、MySQL 数据库操作

> 表 1 学生（Student）表 

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| zhangsan | 69 | 86 | 77 |
| lisi | 55 | 100 | 88 |

### 1. 创建 MySQL 表
根据表 1 ，在 MySQL 中创建 Student 表，并录入数据。

参考命令 (MySQL Shell 命令)
```bash
use test;
create table Student(
    Name varchar(30) not null,
    English tinyint unsigned not null,
    Math tinyint unsigned not null,
    Computer tinyint unsigned not null
);

```

```bash
insert into Student values("zhangsan",69,86,77);
insert into Student values("lisi",55,100,88);

```

### 2. 输出 MySQL 表全部数据
用 SQL 语句输出 Student 表中的所有记录。

参考命令 (MySQL Shell 命令)
```bash
select * from Student;

```

### 3. 查询 MySQL 表数据
查询 zhangsan 的 Computer 成绩。

参考命令 (MySQL Shell 命令)
```bash
select Name , Computer from Student where Name = "zhangsan";

```


### 4. 修改 MySQL 表数据
修改 lisi 的 Math 成绩为 95。

参考命令 (MySQL Shell 命令)
```bash
update Student set Math=95 where Name="lisi";

```

### 5. 使用 Java 编程向 MySQL 表添加数据
向 Student 表 添加如下所示的一条数据：

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| scofield | 45 | 89 | 100 |




### 6. 使用 Java 编程查询 MySQL 表数据
从 Student 表获取 wangwu 的 English 成绩。




## 二、HBase 数据库操作

> 表 2 学生（Student）表 

|Name|Score:English|Score:Math|Score:Computer|
| ---- | ---- | ---- | ---- |
| zhangsan | 69 | 86 | 77 |
| lisi | 55 | 100 | 88 |

### 7. 创建 HBase 表
根据表 2 ，用 HBase Shell 命令创建学生（Student）表。

参考命令 (HBase Shell 命令)
```bash
create 'student','score'

```

```bash
put 'student','zhangsan','score:English','69'
put 'student','zhangsan','score:Math','86'
put 'student','zhangsan','score:Computer','77'
put 'student','lisi','score:English','55'
put 'student','lisi','score:Math','100'
put 'student','lisi','score:Computer','88'

```


### 8. 输出 HBase 表元数据
用 scan 命令浏览 Student 表的相关信息。

参考命令 (HBase Shell 命令)
```bash
scan 'student'

```


### 9. 查询 HBase 表数据
查询 zhangsan 的 Computer 成绩。

参考命令 (HBase Shell 命令)
```bash
get 'student','zhangsan','score:Computer'

```


### 10. 修改 HBase 表数据
修改 lisi 的 Math 成绩为 95。

参考命令 (HBase Shell 命令)
```bash
put 'student','lisi','score:Math','95'

```



### 11. 使用 Java 编程向 HBase 添加数据
向 Student 表中添加如下所示的一条记录：

|Name|English|Math|Computer|
| ---- | ---- | ---- | ---- |
| scofield | 45 | 89 | 100 |

### 12. 使用 Java 编程查询 HBase 表数据
从 Student 表获取 scofield 的 English 成绩信息。

## 三、Redis 数据库操作

> Student 键值对如下：
```js
zhangsan: {
    English: 69
    Math: 86
    Computer: 77
}
lisi: {
    English: 55
    Math: 100
    Computer: 88
}
```


### 13. 创建 Redis 键值结构
用 Redis 的哈希结构设计出 Student 表（键值可以用 student.zhangsan 和 student.lisi 来表示两个键值属于同一个表）。

参考命令 (Redis Shell 命令)
```bash
hset student.zhangsan English 69
hset student.zhangsan Math 86
hset student.zhangsan Computer 77
hset student.lisi English 55
hset student.lisi Math 100
hset student.lisi Computer 88

```

### 14. 输出 Redis 键值数据
用 hgetall 命令分别输出 zhangsan 和 lisi 的成绩信息。

参考命令 (Redis Shell 命令)
```bash
hgetall student.zhangsan

```

```bash
hgetall student.lisi

```

### 15. 查询 Redis 键值数据
用 hget 命令查询 zhangsan 的 Computer 成绩。

参考命令 (Redis Shell 命令)
```bash
hget student.zhangsan Computer

```

### 16. 修改 Redis 键值数据
修改 lisi 的 Math 成绩为 95。

参考命令 (Redis Shell 命令)
```bash
hset student.lisi Math 95

```

### 17. 使用 Java 编程向 Redis 添加数据
向 Student 表中添加如下所示的一条记录。该数据对应的键值对形式如下：

```js
scofield: {
    English: 45
    Math: 89
    Computer: 100
}
```

参考java代码：
```java
import java.util.Map;
import redis.clients.jedis.Jedis;
 
public class jedis_test {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Jedis jedis = new Jedis("localhost");
        jedis.hset("student.scofield", "English","45");
        jedis.hset("student.scofield", "Math","89");
        jedis.hset("student.scofield", "Computer","100");
        Map<String,String>  value = jedis.hgetAll("student.scofield");
        for(Map.Entry<String, String> entry:value.entrySet())
        {
            System.out.println(entry.getKey()+":"+entry.getValue());
        }
    }
}
```

### 18. 使用 Java 编程查询 Redis 键值数据
获取 scofield 的 English 成绩信息。

参考java代码：
```java
import java.util.Map;
import redis.clients.jedis.Jedis;
 
public class jedis_query {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Jedis jedis = new Jedis("localhost");
        String value=jedis.hget("student.scofield", "English");
        System.out.println("scofield's English score is:    "+value);
    }
}
```


## 四、MongoDB 数据库操作

> Student 文档如下：
```js
{
    "name": "zhangsan",
    "score": {
        "English": 69,
        "Math": 86,
        "Computer": 77
    }
}
{
    "name": "lisi",
    "score": {
        "English": 55,
        "Math": 100,
        "Computer": 88
    }
}
```

### 19. 创建 MongoDB 集合
用 MongoDB Shell 设计出 Student 集合。

参考命令 (Mongo Shell 命令)
```bash
use student

```

```bash
var stus=[{"name":"zhangsan","score":{"English":69,"Math":86,"Computer":77}}, {"name":"lisi","score":{"English":55,"Math":100,"Computer":88}}]

```

```bash
db.student.insert(stus)

```

### 20. 输出 MongoDB 集合数据
用 find() 方法输出两个学生的信息。

参考命令 (Mongo Shell 命令)
```bash
db.student.find().pretty()

```

### 21. 查询 MongoDB 文档数据
用 find() 方法查询 zhangsan 的所有成绩（只显示 score 列）。

参考命令 (Mongo Shell 命令)
```bash
db.student.find({"name":"zhangsan"},{"_id":0,"name":0})

```

### 22. 修改 MongoDB 文档数据
修改 lisi 的 Math 成绩为 95。

参考命令 (Mongo Shell 命令)
```bash
db.student.update({"name":"lisi"}, {"$set":{"score.Math":95}} )

```

### 23. 使用 Java 编程向 MongoDB 添加记录
向 Student 表中添加如下所示的一条记录。该数据对应的文档形式如下：

```js
{
    "name": "scofield",
    "score": {
        "English": 45,
        "Math": 89,
        "Computer": 100
    }
}
```

参考java代码：
```java
import java.util.ArrayList;
import java.util.List;
 
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
 
public class mongo_insert {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //实例化一个mongo客户端
        MongoClient  mongoClient=new MongoClient("localhost",27017);
        //实例化一个mongo数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("student");
        //获取数据库中某个集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        //实例化一个文档,内嵌一个子文档
        Document document=new Document("name","scofield").
                append("score", new Document("English",45).
                        append("Math", 89).
                        append("Computer", 100));
        List<Document> documents = new ArrayList<Document>();  
        documents.add(document);  
        //将文档插入集合中
        collection.insertMany(documents);  
        System.out.println("文档插入成功"); 
    }
}
```



### 24. 使用 Java 编程查询 MongoDB 文档数据
获取 scofield 的 English 成绩信息（只显示 score 列）。

参考java代码：
```java
import java.util.ArrayList;
import java.util.List;
 
import org.bson.Document;
import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.eq;
public class mongo_query {
 
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //实例化一个mongo客户端
        MongoClient  mongoClient=new MongoClient("localhost",27017);
        //实例化一个mongo数据库
        MongoDatabase mongoDatabase = mongoClient.getDatabase("student");
        //获取数据库中某个集合
        MongoCollection<Document> collection = mongoDatabase.getCollection("student");
        //进行数据查找,查询条件为name=scofield, 对获取的结果集只显示score这个域
        MongoCursor<Document>  cursor=collection.find( new Document("name","scofield")).
                projection(new Document("score",1).append("_id", 0)).iterator();
        while(cursor.hasNext())
            System.out.println(cursor.next().toJson());
    }
}
```