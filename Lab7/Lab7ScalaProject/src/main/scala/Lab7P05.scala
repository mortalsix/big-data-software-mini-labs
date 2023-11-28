/**
 * 实验 7
 * 问题 5. 编写 Spark 独立应用程序实现数据去重
 */

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