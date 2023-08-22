import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

public class P01Solution {
    public static void main(String[] args) throws IOException {
        String srcfile = "input/p01.txt";         // 本地文件路径
        String dstfile = "/lab2/output/p01.txt";  // HDFS 文件路径

        // 生成必要的 HDFS 配置对象
        Configuration conf = new Configuration();
        // 设置文件系统使用 HDFS
        conf.set("fs.defaultFS", "hdfs://localhost:9000");
        conf.set("dfs.client.block.write.replace-datanode-on-failure.policy", "NEVER");  // 这行代码和下行代码搭配解决“追加文件内容”时的一些bug
        conf.setBoolean("dfs.client.block.write.repalce-datanode-on-failure", true);     // 这行代码和上行代码搭配解决”追加文件内容“时的一些bug

        // 获取文件系统对象
        FileSystem fileSystem = FileSystem.get(conf);
        // 生成 Hadoop 支持的路径对象
        Path srcfile_path = new Path(srcfile);
        Path dstfile_path = new Path(dstfile);

        // 判断 HDFS 文件是否存在
        if (fileSystem.exists(dstfile_path)) {
            // HDFS 文件存在，输出信息提示
            System.out.println(dstfile_path.toString() + " 已存在。");
            // 询问用户是要追加到原有文件末尾，还是要覆盖原 HDFS 文件，将用户的选择记录在 user_choice 字符串中
            System.out.print("是要覆盖原文件还是追加到文件末尾？ 1 表示覆盖， 2 表示追加，请输入：");
            Scanner scanner = new Scanner(System.in);
            String user_choice = scanner.next();
            scanner.close();
            if (user_choice.equals("1")) {
                // 覆盖原文件
                fileSystem.copyFromLocalFile(false, true, srcfile_path, dstfile_path);
                System.out.println(srcfile + " 已上传并覆盖文件 " + dstfile + "。");
            } else if (user_choice.equals("2")) {
                // 追加到文件末尾
                FileInputStream in = new FileInputStream(srcfile);
                FSDataOutputStream out = fileSystem.append(dstfile_path);
                byte[] data = new byte[1024];
                int read = in.read(data);
                while (read > 0) {
                    out.write(data);
                    read = in.read(data);
                }
                out.close();
                in.close();
                System.out.println(srcfile + " 内容已追加到 " + dstfile + " 文件末尾。");
            }
        } else {
            // HDFS 文件不存在，输出提示并直接上传文件
            System.out.println(dstfile_path.toString() + " 不存在。");
            fileSystem.copyFromLocalFile(srcfile_path, dstfile_path);
            System.out.println(srcfile + " 已上传至 " + dstfile + "。");
        }
        // 不管作何处理，最终要关闭 Hadoop 文件系统对象
        fileSystem.close();
    }
}