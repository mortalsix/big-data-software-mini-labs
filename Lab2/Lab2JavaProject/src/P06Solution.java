import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.*;
import java.io.*;
 
public class P06Solution {
    /**
     * 判断路径是否存在
     */
    public static boolean test(Configuration conf, String path) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        return fs.exists(new Path(path));
    }
 
    /**
     * 创建目录
     */
    public static boolean mkdir(Configuration conf, String remoteDir) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path dirPath = new Path(remoteDir);
        boolean result = fs.mkdirs(dirPath);
        fs.close();
        return result;
    }
 
    /**
     * 创建文件
     */
    public static void touchz(Configuration conf, String remoteFilePath) throws IOException {
        FileSystem fs = FileSystem.get(conf);
        Path remotePath = new Path(remoteFilePath);
        FSDataOutputStream outputStream = fs.create(remotePath);
        outputStream.close();
        fs.close();
    }
 
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
        String remoteFilePath = "/user/hadoop/input/text.txt";    // HDFS路径
        String remoteDir = "/user/hadoop/input";    // HDFS路径对应的目录
 
        try {
            /* 判断路径是否存在，存在则删除，否则进行创建 */
            if ( P06Solution.test(conf, remoteFilePath) ) {
                P06Solution.rm(conf, remoteFilePath); // 删除
                System.out.println("删除路径: " + remoteFilePath);
            } else {
                if ( !P06Solution.test(conf, remoteDir) ) { // 若目录不存在，则进行创建
                    P06Solution.mkdir(conf, remoteDir);
                    System.out.println("创建文件夹: " + remoteDir);
                }
                P06Solution.touchz(conf, remoteFilePath);
                System.out.println("创建路径: " + remoteFilePath);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}