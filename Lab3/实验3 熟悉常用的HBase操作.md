# 实验目的
- 理解 HBase 在 Hadoop 体系结构中的角色。
- 熟练使用 HBase 操作常用的 Shell 命令。
- 熟悉 HBase 操作常用的 Java API。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- HBase 版本：2.5.4
- Java IDE：Visual Studio Code

# 实验内容和要求
### 一、编程实现以下指定功能，并用 Hadoop 提供的 HBase Shell 命令完成相同的任务。
##### 1. 列出 HBase 所有表。

参考命令 (HBase Shell 命令)
```bash
list

```

参考java代码：
```java
public static void listTables() throws IOException {
    init();//建立连接
    List<TableDescriptor> tableDescriptors = admin.listTableDescriptors();
    for(TableDescriptor tableDescriptor : tableDescriptors){
        TableName tableName = tableDescriptor.getTableName();
        System.out.println("Table:" + tableName);
    }
    close();//关闭连接
}
```

##### 2. 在终端输出指定表的所有记录数据。

参考命令 (HBase Shell 命令)
```bash
scan 't1'

```

参考java代码：
```java
//在终端打印出指定的表的所有记录数据
public static void getData(String tableName)throws  IOException{
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();
    ResultScanner scanner = table.getScanner(scan);//获取行的遍历器
    for (Result result:scanner){
        printRecoder(result);
    }
    close();
}
//打印一条记录的详情
public  static void printRecoder(Result result)throws IOException{
    for(Cell cell:result.rawCells()){
        System.out.print("行健: "+new String(Bytes.toString(cell.getRowArray(),cell.getRowOffset(), cell.getRowLength())));
        System.out.print("列簇: "+new String( Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(), cell.getFamilyLength()) ));
        System.out.print(" 列: "+new String(Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(), cell.getQualifierLength())));
        System.out.print(" 值: "+new String(Bytes.toString(cell.getValueArray(),cell.getValueOffset(), cell.getValueLength())));
        System.out.println("时间戳: "+cell.getTimestamp());            
    }
}
```

##### 3. 向已经创建好的表添加数据。


参考命令 (HBase Shell 命令)
```bash
create 's1','score'
put 's1','zhangsan','score:Math','69'
delete 's1','zhangsan','score:Math'

```

参考java代码：
```java
//向表添加数据
public static void insterRow(String tableName,String rowKey,String colFamily,String col,String val) throws IOException {
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Put put = new Put(rowKey.getBytes());
    put.addColumn(colFamily.getBytes(), col.getBytes(), val.getBytes());
    table.put(put);
    table.close();
    close();
}
//删除数据
public static void deleRow(String tableName,String rowKey,String colFamily,String col) throws IOException {
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Delete delete = new Delete(rowKey.getBytes());
    //删除指定列族
    delete.addFamily(Bytes.toBytes(colFamily));
    //删除指定列
    delete.addColumn(Bytes.toBytes(colFamily),Bytes.toBytes(col));
    table.delete(delete);
    table.close();
    close();
}
```

##### 4. 统计表的行数。

参考命令 (HBase Shell 命令)
```bash
count 's1'

```

参考java代码：
```java
//统计表的行数
public static void countRows(String tableName)throws IOException{
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();
    ResultScanner scanner = table.getScanner(scan);
    int num = 0;
    for (Result result = scanner.next();result!=null;result=scanner.next()){
        num++;
    }
    System.out.println("行数:"+ num);
    scanner.close();
    close();
}
```


##### 5. 清空指定表的所有记录数据。

参考命令 (HBase Shell 命令)
```bash
truncate 's1'

```

参考java代码：
```java
//清空指定的表的所有记录数据
public static void clearRows(String tableName)throws IOException{
    init();
    TableName tablename = TableName.valueOf(tableName);
    admin.disableTable(tablename);
    admin.deleteTable(tablename);
    TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tablename);
    admin.createTable(tableDescriptor.build());        
    close();
}
```

### 二、现有以下关系数据库中的表（见表1、表2和表3），完成指定任务。

> 表 1 学生（Student）表 

|学号（S_No）|姓名（S_Name）|性别（S_Sex）|年龄（S_Age）|
| ---- | ---- | ---- | ---- |
| 2015001 | Zhangsan | male | 23 |
| 2015002 | Mary | female | 22 |
| 2015003 | Lisi | male | 24 |


> 表 2 课程（Course）表 

|课程号（C_No）|课程名（C_Name）|学号（C_Credit）|
| ---- | ---- | ---- |
| 123001 | Math | 2.0 |
| 123002 | Computer Science | 5.0 |
| 123003 | English | 3.0 |


> 表 3 选课（SC）表 

|学号（SC_No）|课程号（SC_No）|成绩（SC_Score）|
| ---- | ---- | ---- |
| 2015001 | 123001 | 86 |
| 2015001 | 123003 | 69 |
| 2015002 | 123002 | 77 |
| 2015002 | 123003 | 99 |
| 2015003 | 123001 | 98 |
| 2015003 | 123002 | 95 |

##### 6. 创建表并插入数据

将表1、表2和表3中的数据转换为适合 HBase 存储的表，并使用 HBase Shell 命令插入数据。

参考命令 (在 HBase Shell 中输入以下命令)

创建`Student`表并添加数据：
```bash
create 'Student','S_No','S_Name','S_Sex','S_Age'
put 'Student','s001','S_No','2015001'
put 'Student','s001','S_No','2015001'
put 'Student','s001','S_Name','Zhangsan'
put 'Student','s001','S_Sex','male'
put 'Student','s001','S_Age','23'
put 'Student','s002','S_No','2015002'
put 'Student','s002','S_Name','Mary'
put 'Student','s002','S_Sex','female'
put 'Student','s002','S_Age','22'
put 'Student','s003','S_No','2015003'
put 'Student','s003','S_Name','Lisi'
put 'Student','s003','S_Sex','male'
put 'Student','s003','S_Age','24'

```

创建`Course`表并添加数据：
```bash
create 'Course','C_No','C_Name','C_Credit'
put 'Course','c001','C_No','123001'
put 'Course','c001','C_Name','Math'
put 'Course','c001','C_Credit','2.0'
put 'Course','c002','C_No','123002'
put 'Course','c002','C_Name','Computer'
put 'Course','c002','C_Credit','5.0'
put 'Course','c003','C_No','123003'
put 'Course','c003','C_Name','English'
put 'Course','c003','C_Credit','3.0'

```

创建`SC`表并添加数据：
```bash
create 'SC','SC_Sno','SC_Cno','SC_Score'
put 'SC','sc001','SC_Sno','2015001'
put 'SC','sc001','SC_Cno','123001'
put 'SC','sc001','SC_Score','86'
put 'SC','sc002','SC_Sno','2015001'
put 'SC','sc002','SC_Cno','123003'
put 'SC','sc002','SC_Score','69'
put 'SC','sc003','SC_Sno','2015002'
put 'SC','sc003','SC_Cno','123002'
put 'SC','sc003','SC_Score','77'
put 'SC','sc004','SC_Sno','2015002'
put 'SC','sc004','SC_Cno','123003'
put 'SC','sc004','SC_Score','99'
put 'SC','sc005','SC_Sno','2015003'
put 'SC','sc005','SC_Cno','123001'
put 'SC','sc005','SC_Score','98'
put 'SC','sc006','SC_Sno','2015003'
put 'SC','sc006','SC_Cno','123002'
put 'SC','sc006','SC_Score','95'

```


##### 7. 编程实现功能 `createTable(String tableName, String[] fields)` 

功能描述：创建表，参数 tableName 为表的名称，字符串数组 fields 为存储记录各个域名称的数组。要求当 HBase 已经存在名为 tableName 的表的时候，先删除原有的表，再创建新的表。

参考java代码：
```java
public static void createTable(String tableName,String[] fields) throws IOException {
    init();
    TableName tablename = TableName.valueOf(tableName);

    if(admin.tableExists(tablename)){
        System.out.println("table is exists!");
        admin.disableTable(tablename);
        admin.deleteTable(tablename);//删除原来的表
    }

    TableDescriptorBuilder tableDescriptor = TableDescriptorBuilder.newBuilder(tablename);
    for(String str : fields){
        tableDescriptor.setColumnFamily(ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(str)).build());
    }
            admin.createTable(tableDescriptor.build());
    close();
}
```

##### 8. 编程实现功能 `addRecord(String tableName, String row, String[] fields, String[] values)` 

功能描述：向表 tableName、行 row（用 S_Name 表示）和字符串数组fields指定的单元格中添加对应的数据 values。其中，如果fields中每个元素对应的列族下还有相应的列限定符， 用 columnFamily:column 表示。 例如同时向 Math、 Computer Science、 English 3列添加成绩时， 字符串数组 fields 为 {"Score:Math", "Score:Computer Science", "Score:English"}， 数组 values 存储这3门课的成绩。

参考java代码：
```java
public static void addRecord(String tableName,String row,String[] fields,String[] values) throws IOException {
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    for(int i = 0;i != fields.length;i++){
        Put put = new Put(row.getBytes());
        String[] cols = fields[i].split(":");
        put.addColumn(cols[0].getBytes(), cols[1].getBytes(), values[i].getBytes());
        table.put(put);
    }
    table.close();
    close();
}
```

##### 9. 编程实现功能 `scanColumn(String tableName, String column)`  

功能描述：浏览表 tableName 某一列的数据， 如果某一行记录中该列数据不存在， 则返回 null 。 要求当参数  column 为某一列族名称时， 如果底下有若干个列限定符， 则列出每个列限定符代表的列的数据； 当参数 column 为某一列具体名称（ 如 Score:Math ） 时， 只需要列出该列的数据。

参考java代码：
```java
public static void scanColumn(String tableName,String column)throws  IOException{
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Scan scan = new Scan();
    scan.addFamily(Bytes.toBytes(column));
    ResultScanner scanner = table.getScanner(scan);
    for (Result result = scanner.next(); result != null; result = scanner.next()){
        showCell(result);
    }
    table.close();
    close();
}
//格式化输出
public static void showCell(Result result){
    Cell[] cells = result.rawCells();
    for(Cell cell:cells){
        System.out.println("RowName:"+new String(Bytes.toString(cell.getRowArray(),cell.getRowOffset(), cell.getRowLength()))+" ");
        System.out.println("Timetamp:"+cell.getTimestamp()+" ");
        System.out.println("column Family:"+new String(Bytes.toString(cell.getFamilyArray(),cell.getFamilyOffset(), cell.getFamilyLength()))+" ");
        System.out.println("row Name:"+new String(Bytes.toString(cell.getQualifierArray(),cell.getQualifierOffset(), cell.getQualifierLength()))+" ");
        System.out.println("value:"+new String(Bytes.toString(cell.getValueArray(),cell.getValueOffset(), cell.getValueLength()))+" ");           
    }
}
```

##### 10. 编程实现功能 `modifyData(String tableName, String row, String column)` 

功能描述：修改表 tableName、 行 row （可以用学生姓名 S_Name 表示） 、 列 column 指定的单元格的数据。

参考java代码：
```java
public static void modifyData(String tableName,String row,String column,String val)throws IOException{
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Put put = new Put(row.getBytes());
    put.addColumn(column.getBytes(),null,val.getBytes());
    table.put(put);
    table.close();
    close();
}
```

##### 11. 编程实现功能 `deleteRow(String tableName, String row)` 

功能描述：删除表 tableName 中 row 指定的行的记录。

参考java代码：
```java
public static void deleteRow(String tableName,String row)throws IOException{
    init();
    Table table = connection.getTable(TableName.valueOf(tableName));
    Delete delete = new Delete(row.getBytes());        
    table.delete(delete);
    table.close();
    close();
}
```