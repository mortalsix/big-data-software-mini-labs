import java.io.IOException;
import java.util.List;

public class Lab3P01 {
    public static void listTables() throws IOException {
        init();//建立连接
        List<TableDescriptor> tableDescriptors = admin.listTableDescriptors();
        for(TableDescriptor tableDescriptor : tableDescriptors){
            TableName tableName = tableDescriptor.getTableName();
            System.out.println("Table:" + tableName);
        }
        close();//关闭连接
    }
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}
