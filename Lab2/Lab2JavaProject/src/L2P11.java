/**
 * 实验 2 
 * 问题 11. 编程实现按行读取指定 HDFS 文件内容
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.*;
 
public class L2P11 {
    /**
     * 实现按行读取
     * 每次读入一个字符，遇到"\n"结束，返回一行内容
     */
    public static String readline(BufferedReader br) throws IOException {
        char[] data = new char[1024];
        int off = 0; 
        // 循环执行时，br 每次会从上一次读取结束的位置继续读取
        //因此该函数里，off 每次都从0开始
        while ( (br.read(data, off, 1)) != -1 ) {
            if (String.valueOf(data[off]).equals("\n") ) {
                off += 1;
                break;
            }
            off += 1;
        }
 
        if (off > 0) {
            return String.valueOf(data);
        } else {
            return null;
        }
    }
 
    /**
     * 读取文件内容
     */
    public static void cat(Configuration conf, String remoteFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(remoteFilePath);
        FSDataInputStream in = fs.open(remotePath);
        BufferedReader br = new BufferedReader(new InputStreamReader(in));
        String line = null;
        while ( (line = L2P11.readline(br)) != null ) {
            System.out.println(line);
        }
        br.close();
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
            L2P11.cat(conf, remoteFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}