/**
 * 实验 2 
 * 问题 10. 移动 HDFS 文件或目录
 * 在 HDFS 中将文件从源路径移动到目的路径。
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;
 
public class L2P10 {
    /**
     * 移动文件
     */
    public static boolean mv(Configuration conf, String remoteFilePath, String remoteToFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path srcPath = new Path(remoteFilePath);
        Path dstPath = new Path(remoteToFilePath);
        boolean result = fs.rename(srcPath, dstPath);
        fs.close();
        return result;
    }
 
    /**
     * 主函数
     */
    public static void main(String[] args) {
        Configuration conf = new Configuration();
    conf.set("fs.default.name","hdfs://localhost:9000");
        String remoteFilePath = "hdfs:///user/hadoop/text.txt";    // 源文件HDFS路径
        String remoteToFilePath = "hdfs:///user/hadoop/new.txt";    // 目的HDFS路径
 
        try {
            if ( L2P10.mv(conf, remoteFilePath, remoteToFilePath) ) {
                System.out.println("将文件 " + remoteFilePath + " 移动到 " + remoteToFilePath);
            } else {
                    System.out.println("操作失败(源文件不存在或移动失败)");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}