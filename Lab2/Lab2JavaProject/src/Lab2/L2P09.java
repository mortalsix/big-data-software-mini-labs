package Lab2;
/**
 * 实验 2 
 * 问题 9. 删除 HDFS 文件
 * 删除 HDFS 中指定的文件。
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;
 
public class L2P09 {
    /**
     * 删除文件
     */
    public static boolean rm(Configuration conf, String remoteFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(remoteFilePath);
        boolean result = fs.delete(remotePath, false);
        fs.close();
        return result;
    }
 
    /**
     * 主函数
     */
    public static void main(String[] args) {
        Configuration conf = new Configuration();
    conf.set("fs.default.name","hdfs://localhost:9000");
        String remoteFilePath = "/user/hadoop/text.txt";    // HDFS文件
 
        try {
            if ( L2P09.rm(conf, remoteFilePath) ) {
                System.out.println("文件删除: " + remoteFilePath);
            } else {
                System.out.println("操作失败（文件不存在或删除失败）");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}