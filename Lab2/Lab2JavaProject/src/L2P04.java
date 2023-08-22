/**
 * 实验 2 
 * 问题 4. 输出 HDFS 文件详细信息
 * 显示 HDFS 中指定的文件读写权限、大小、创建时间、路径等信息。
 */

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;
import java.text.SimpleDateFormat;
 
public class P04Solution {
    /**
     * 显示指定文件的信息
     */
    public static void ls(Configuration conf, String remoteFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(remoteFilePath);
        FileStatus[] fileStatuses = fs.listStatus(remotePath);
        for (FileStatus s : fileStatuses) {
            System.out.println("路径: " + s.getPath().toString());
            System.out.println("权限: " + s.getPermission().toString());
            System.out.println("大小: " + s.getLen());
            /* 返回的是时间戳,转化为时间日期格式 */
            Long timeStamp = s.getModificationTime();
            SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String date = format.format(timeStamp);  
            System.out.println("时间: " + date);
        }
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
            System.out.println("读取文件信息: " + remoteFilePath);
            P04Solution.ls(conf, remoteFilePath);
            System.out.println("\n读取完成");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}