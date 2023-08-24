package Lab3;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;;

/**
 * 实验 3 
 * 问题 2. 在终端输出指定表的所有记录数据。
 */

public class Lab3P02 {
    public static Configuration configuration;
    public static Connection connection;
    public static Admin admin;
    public static void init(){
        configuration  = HBaseConfiguration.create();
        try{
            connection = ConnectionFactory.createConnection(configuration);
            admin = connection.getAdmin();
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    public static void close(){
        try{
            if(admin != null){
                admin.close();
            }
            if(null != connection){
                connection.close();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
