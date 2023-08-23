/**
 * 实验 2 
 * 问题 2. 从 HDFS 下载文件
 * 从 HDFS 中下载指定文件，如果本地文件与要下载的文件名称相同，
 * 则自动对下载的文件重命名。
 */

import java.io.File;
import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class L2P02 {
    public static void main(String[] args) throws IOException {
        String srcfile = "/lab2/input/p02.txt";  // HDFS 文件路径
        String dstfile = "output/p02.txt";       // 本地文件路径

        // 生成必要的 HDFS 配置对象
        Configuration conf = new Configuration();
        // 设置文件系统使用 HDFS
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        // 获取文件系统对象
        FileSystem fileSystem = FileSystem.get(conf);
        
        File f = new File(dstfile);
        if (f.exists()) {
            System.out.println(dstfile + " 已存在。");
            dstfile = regenerateFilePath(dstfile);
            System.out.println("将重新命名为: " + dstfile);
        }

        // 生成 Hadoop 支持的路径对象
        Path srcfile_path = new Path(srcfile);
        Path dstfile_path = new Path(dstfile);

        fileSystem.copyToLocalFile(srcfile_path, dstfile_path);
        System.out.println("下载完成");
    }

    private static String regenerateFilePath(String dstfile) {
        Integer i = 2;
        String renamed_file = dstfile + "_" + i.toString();
        File f = new File(renamed_file);
        while (f.exists()) {
            i++;
            renamed_file = dstfile + "_" + i.toString();
            f = new File(renamed_file);
        }
        return renamed_file;
    }
}
