// 参考代码：使用 Spark RDD 实现 PageRank 算法

import org.apache.spark.SparkContext
import org.apache.spark.SparkContext._
import org.apache.spark.SparkConf

object PageRankApp {
    def main(args: Array[String]) {
        ////////////////////////////////////////////////////////////
        // 输入文件说明：
        //    每一行均为两个url地址，中间用一个空格隔开的文本文件
        // 输入文件内容示例：
        //     u1 u3
        //     u1 u1
        //     u2 u3
        //     u2 u2
        //     u3 u1
        // 输出文件说明：
        //    每一行均为一个url地址和其PageRank值，中间用英文逗号隔开
        // 输出文件内容示例：
        //     (u1,1.739)
        //     (u2,0.261)
        //     (u3,1.0)
        ////////////////////////////////////////////////////////////

        val inputFilePath = "file:///home/fzw/data/pagerank/input/test.txt"
        val outputFolderPath = "file:///home/fzw/data/pagerank/output"

        val conf = new SparkConf().setAppName("PageRankApp")
        val sc = new SparkContext(conf)

        val lines = sc.textFile(inputFilePath)
        val links1 = lines.map(line => (line.split(" ")(0), line.split(" ")(1)))
        val links2 = links1.distinct()
        val links3 = links2.groupByKey()
        val links4 = links3.persist()

        var ranks = links4.mapValues(v => 1.0)

        for (i <- 1 to 10) {
            val jj = links4.join(ranks)
            val contribs = jj.values.flatMap{
                case (urls, rank) => 
                    urls.map(url => (url, rank / urls.size))
            }
            ranks = contribs.reduceByKey(_ + _).mapValues(0.15 + 0.85 * _)
        }

        ranks.saveAsTextFile(outputFolderPath)
    }
}