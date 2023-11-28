# 1. 安装并配置sbt

参考 %E5%9C%A8Linux%E4%B8%AD%E5%AE%89%E8%A3%85%E5%B9%B6%E9%85%8D%E7%BD%AEsbt.md


# 2. 创建scala应用程序代码目录

```bash
cd ~
mkdir -p ./sparkapps/test # 为你的scala应用程序创建一个文件夹，并在其中创建第一个项目 test 
mkdir -p ./sparkapps/test/src/main/scala # 在test中创建scala程序目录结构
touch ./sparkapps/test/src/main/scala/SimpleApp.scala # 创建scala程序文件
```

# 3. 使用合适的编辑器打开scala程序文件完成代码编写

在`SimpleApp.scala`中添加如下代码：

```scala
/* SimpleApp.scala */
import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf
 
object SimpleApp {
        def main(args: Array[String]) {
            val logFile = "file:///usr/local/spark/README.md" // Should be some file on your system
            val conf = new SparkConf().setAppName("Simple Application")
            val sc = new SparkContext(conf)
            val logData = sc.textFile(logFile, 2).cache()
            val numAs = logData.filter(line => line.contains("a")).count()
            val numBs = logData.filter(line => line.contains("b")).count()
            println("Lines with a: %s, Lines with b: %s".format(numAs, numBs))
        }
    }
```

# 4. 使用 sbt 对 Spark scala 应用程序打包

该程序依赖 Spark API，因此需要通过 sbt 编译打包：

```bash
cd ~/sparkapps/test  # 进入test项目目录
touch simple.sbt # 创建 sbt 文件
```

使用合适的文本编辑器向 `simple.sbt` 文件中添加如下内容：

```
name := "Simple Project"
version := "1.0"
scalaVersion := "2.12.17"
libraryDependencies += "org.apache.spark" %% "spark-core" % "3.4.0"
```

为保证sbt能正常运行，先使用如下命令检查应用程序的目录结构：

```bash
cd ~/sparkapps/test
find .
```

结果应该如下所示：

![Alt text](imgs/spark%E5%BA%94%E7%94%A8%E7%9B%AE%E5%BD%95%E7%BB%93%E6%9E%84.png)

最后，使用sbt程序执行编译打包：

```bash
/usr/local/sbt/sbt package
```

生成的 jar 包位置在 `~/sparkapps/test/target/scala-2.12/simple-project_2.12-1.0.jar`


# 5. 使用 spark-submit 运行程序

命令如下：

```bash
/usr/local/spark/bin/spark-submit --class "SimpleApp" ~/sparkapps/test/target/scala-2.12/simple-project_2.12-1.0.jar 2>&1 | grep "Lines with a:"
```

结果如下：

![Alt text](imgs/spark%E6%B5%8B%E8%AF%95%E5%BA%94%E7%94%A8%E8%BE%93%E5%87%BA%E7%BB%93%E6%9E%9C.png)