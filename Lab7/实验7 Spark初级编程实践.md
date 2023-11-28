# 实验目的
- 通过使用 Spark 访问本地文件和 HDFS 文件的方法。
- 掌握 Spark 应用程序的编写、编译和运行方法。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- Spark 版本：3.4.0

# 实验内容和要求
### 1. 安装 Hadoop 和 Spark

完成 Hadoop 伪分布式模式的安装后，进行 Spark（Local 模式）的安装。

### 2. 使用 Spark Shell 读取本地文件

启动 spark-shell：
```bash
cd /usr/local/spark
./bin/spark-shell
```

在 spark-shell 中读取本地文件 /tmp/test.txt 并统计出文件的行数

参考代码（spark-shell）：
```scala
val textFile=sc.textFile("file:///tmp/test.txt")
textFile.count()
```

### 3. 使用 Spark Shell 读取 HDFS 文件

在 Spark-shell 中读取 HDFS 文件 /test/test.txt 并统计出文件的行数

参考代码（spark-shell）：
```scala
val textFile=sc.textFile("hdfs://localhost:9000/test/test.txt")
textFile.count()
```

### 4. 使用 Spark 编程读取 HDFS 文件

编写独立应用程序，读取 HDFS 文件 /test/test.txt ，然后统计出文件的行数；通过 sbt 将整个应用程序编译打包成 JAR 包，并将生成的 JAR 包通过 spark-submit 提交到 Spark 中运行。

参考过程：

(1) 创建 scala 应用程序目录和程序文件
```bash
mkdir -p ~/sparkapps/readhdfsfile
mkdir -p ~/sparkapps/readhdfsfile/src/main/scala
touch ~/sparkapps/readhdfsfile/src/main/scala/SparkReadHdfsFile.scala
``` 

(2) 编写 scala 程序：

参考 scala 程序代码：
```scala
/* SparkReadHdfsFile.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
object ReadHdfsFileApp {
    def main(args: Array[String]) {
        val hdfsFile = "hdfs://localhost:9000/test.txt"
        val conf = new SparkConf().setAppName("Read HDFS File Test")
        val sc = new SparkContext(conf)
        val fileData = sc.textFile(hdfsFile, 2)
        val num = fileData.count()
        printf("The num of this file is %d", num)
    }
}
```

(3) 创建sbt文件
```bash
cd ~/sparkapps/readhdfsfile  # 进入readhdfsfile项目目录
touch simple.sbt # 创建 sbt 文件
```

向 `simple.sbt` 文件添加如下内容：
```
name := "Simple Project"
version := "1.0"
scalaVersion := "2.12.17"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0"
```

为保证sbt能正常运行，先使用命令检查应用程序的目录结构：

```bash
cd ~/sparkapps/readhdfsfile
find .
```

如果文件结构和下图类似（注意，程序文件名可能会不同）：

![Alt text](imgs/spark%E5%BA%94%E7%94%A8%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)

(4) 使用sbt程序执行编译打包：

```bash
/usr/local/sbt/sbt package
```

生成的 jar 包位置在 `~/sparkapps/readhdfsfile/target/scala-2.12/simple-project_2.12-1.0.jar`

(5) 使用 spark-submit 运行程序

注意：在提交之前一定要确保 hdfs 服务已经启动，且 hdfs 中有文本文件 `/test.txt` 

命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "ReadHdfsFileApp" ~/sparkapps/readhdfsfile/target/scala-2.12/simple-project_2.12-1.0.jar 2>&1 | grep "The num of this file is"
```

结果如下：

![Alt text](imgs/spark%E6%B5%8B%E8%AF%95%E5%BA%94%E7%94%A8%E8%BE%93%E5%87%BA%E7%BB%93%E6%9E%9C1.png)


### 5. 编写 Spark 独立应用程序实现数据去重

对于两个输入文件 A 和 B，编写 Spark 独立应用程序，对两个文件进行合并，并剔除其中重复的内容，得到一个新文件 C。下面是输入文件和输出文件的一个样例，可供参考。

输入文件 A 的样例如下：

```
20210501 x
20210502 y
20210503 x
20210504 y
20210505 z
20210506 x
```

输入文件 B 的样例如下：

```
20210501 y
20210502 y
20210503 x
20210504 z
20210505 y
```

根据输入文件 A 和 B 合并得到的输出文件 C 的样例如下：

```
20210501 x
20210501 y
20210502 y
20210503 x
20210504 y
20210504 z
20210505 z
20210505 y
20210506 x
```

参考过程：

(1) 创建 scala 应用程序目录和程序文件
```bash
mkdir -p ~/sparkapps/remdup
mkdir -p ~/sparkapps/remdup/src/main/scala
touch ~/sparkapps/remdup/src/main/scala/SparkRemoveDup.scala
``` 

(2) 编写 scala 程序：

参考 scala 程序代码：
```scala
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.HashPartitioner
 
object RemDup {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("RemDup")
        val sc = new SparkContext(conf)
        val dataFile = "file:///home/fzw/data/remdup"
        val data = sc.textFile(dataFile,2)
        val res = data.filter(_.trim().length>0).map(line=>(line.trim,"")).partitionBy(new HashPartitioner(1)).groupByKey().sortByKey().keys
        res.saveAsTextFile("file:///home/fzw/result/remdup")
    }
}
```

(3) 创建sbt文件
```bash
cd ~/sparkapps/remdup  # 进入remdup项目目录
touch simple.sbt # 创建 sbt 文件
```

向 `simple.sbt` 文件添加如下内容：
```
name := "Simple Project"
version := "1.0"
scalaVersion := "2.12.17"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0"
```

为保证sbt能正常运行，先使用命令检查应用程序的目录结构：

```bash
cd ~/sparkapps/remdup
find .
```

如果文件结构和下图类似（注意，程序文件名可能会不同）：

![Alt text](imgs/spark%E5%BA%94%E7%94%A8%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)

(4) 使用sbt程序执行编译打包：

```bash
/usr/local/sbt/sbt package
```

生成的 jar 包位置在 `~/sparkapps/remdup/target/scala-2.12/simple-project_2.12-1.0.jar`

(5) 使用 spark-submit 运行程序

注意：在提交之前一定要确保输入路径 `/home/fzw/data`（注意，此文件夹根据自己实际目录情况做调整） 中有待处理的文件！！！！ 

命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "RemDup" ~/sparkapps/remdup/target/scala-2.12/simple-project_2.12-1.0.jar
```

最后查看输出路径 `/home/fzw/result/remdup`（注意，此文件夹根据自己实际目录情况做调整） 中是否有结果文件


### 6. 编写 Spark 独立应用程序实现求平均值

每个输入文件表示班级学生某个学科的成绩，每行内容由两个字段组成，第一个是学生名字，第二个是学生的成绩；编写 Spark 独立应用程序求出所有学生的平均成绩，并输出到一个新文件中。下面是输入文件和输出文件的一个样例，可供参考。

Algorithm 成绩：

```
小明 92
小红 87
小新 82
小丽 90
```

Database 成绩：

```
小明 95
小红 81
小新 89
小丽 85
```

Python 成绩：

```
小明 82
小红 83
小新 94
小丽 91
```

平均成绩如下：

```
小红 83.67
小新 88.33
小明 89.67
小丽 88.67
```

参考过程：

(1) 创建 scala 应用程序目录和程序文件
```bash
mkdir -p ~/sparkapps/avgscore
mkdir -p ~/sparkapps/avgscore/src/main/scala
touch ~/sparkapps/avgscore/src/main/scala/SparkAvgScore.scala
``` 

(2) 编写 scala 程序：

参考 scala 程序代码：
```scala
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
import org.apache.spark.HashPartitioner
 
object AvgScore {
    def main(args: Array[String]) {
        val conf = new SparkConf().setAppName("AvgScore")
        val sc = new SparkContext(conf)
        val dataFile = "file:///home/fzw/data/avgscore"
        val data = sc.textFile(dataFile, 3)
 
       val res = data.filter(_.trim().length>0).map(line=>(line.split(" ")(0).trim(),line.split(" ")(1).trim().toInt)).partitionBy(new HashPartitioner(1)).groupByKey().map(x => {
       var n = 0
       var sum = 0.0
       for(i <- x._2){
        sum = sum + i
        n = n +1
       }
       val avg = sum/n
       val format = f"$avg%1.2f".toDouble
       (x._1,format)
     })
       res.saveAsTextFile("result")
    }
}
```

(3) 创建sbt文件
```bash
cd ~/sparkapps/avgscore  # 进入avgscore项目目录
touch simple.sbt # 创建 sbt 文件
```

向 `simple.sbt` 文件添加如下内容：
```
name := "Simple Project"
version := "1.0"
scalaVersion := "2.12.17"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0"
```

为保证sbt能正常运行，先使用命令检查应用程序的目录结构：

```bash
cd ~/sparkapps/avgscore
find .
```

如果文件结构和下图类似（注意，程序文件名可能会不同）：

![Alt text](imgs/spark%E5%BA%94%E7%94%A8%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)

(4) 使用sbt程序执行编译打包：

```bash
/usr/local/sbt/sbt package
```

生成的 jar 包位置在 `~/sparkapps/avgscore/target/scala-2.12/simple-project_2.12-1.0.jar`

(5) 使用 spark-submit 运行程序

注意：在提交之前一定要确保输入路径 `/home/fzw/data/avgscore`（注意，此文件夹根据自己实际目录情况做调整） 中有待处理的文件！！！！ 

命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "AvgScore" ~/sparkapps/avgscore/target/scala-2.12/simple-project_2.12-1.0.jar
```

最后查看输出路径 `/home/fzw/result/avgscore`（注意，此文件夹根据自己实际目录情况做调整） 中是否有结果文件
