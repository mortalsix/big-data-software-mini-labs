/**
 * 实验 7
 * 问题 4. 使用 Spark 编程读取 HDFS 文件
 */

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