/**
 * 实验 2 
 * 问题 3. 输出 HDFS 文件内容
 * 将 HDFS 中指定文件的内容输出到终端。
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;
 
public class L2P03 {
    /**
     * 读取文件内容
     */
    public static void cat(Configuration conf, String remoteFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(remoteFilePath);
        FSDataInputStream in = fs.open(remotePath);
        BufferedReader d = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ( (line = d.readLine()) != null ) {
            System.out.println(line);
        }
       d.close();
       in.close();
       fs.close();
    }
 
    /**
     * 主函数
     */
    public static void main(String[] args) {
        Configuration conf = new Configuration();
    conf.set("fs.default.name","hdfs://localhost:9000");
        String remoteFilePath = "/user/hadoop/text.txt";    // HDFS路径
 
        try {
            System.out.println("读取文件: " + remoteFilePath);
            P03Solution.cat(conf, remoteFilePath);
            System.out.println("\n读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}