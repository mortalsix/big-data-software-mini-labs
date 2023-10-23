# 实验目的
- 通过实验掌握基本的 MapReduce 编程方法。
- 掌握用 MapReduce 解决一些常见数据处理问题的方法，包括数据合并、数据去重、数据排序和数据挖掘等。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- Hive 版本：3.1.3

# 实验内容和要求
### 1. 创建内部表
创建一个内部表 stocks ，字段分隔符为英文逗号，表结构如表 1 所示：

> 表 1 stocks 表结构 

|col_name|data_type|
| ---- | ---- |
| exchange | string |
| symbol | string |
| ymd | string |
| price_open | float |
| price_high | float |
| price_low | float |
| price_close | float |
| volume | int |
| price_adj_close | float |

参考代码（Hive QL）：
```sql
create table if not exists stocks
(
    `exchange` string,
    `symbol` string,
    `ymd` string,
    `price_open` float,
    `price_high` float,
    `price_low` float,
    `price_close` float,
    `volume` int,
    `price_adj_close` float
)
row format delimited fields terminated by ',';
```


### 2. 创建外部分区表

创建一个外部分区表 dividends （分区字段为 exchange 和 symbol ），字段分隔符为英文逗号，表结构如表 2 所示：

> 表 2 dividends 表结构 

|col_name|data_type|
| ---- | ---- |
| ymd | string |
| dividend | float |
| exchange | string |
| symbol | string |

参考代码（Hive QL）：
```sql
create external table if not exists dividends
(
    `ymd` string,
    `dividend` float
)
partitioned by(`exchange` string ,`symbol` string)
row format delimited fields terminated by ',';
```

### 3. 导入数据到 Hive 内部表

从 stocks.csv 文件向 stocks 表中导入数据。

参考代码（Hive QL）：
```sql
load data local inpath '/home/hadoop/data/stocks/stocks.csv' overwrite into table stocks;
```

### 4. 创建未分区外部表并导入数据

创建一个未分区的外部表 dividends_unpartitioned ，并从 dividends.csv 向其中导入数据，表结构如表 3 所示：

> 表 3 dividends_unpartitioned 表结构 

|col_name|data_type|
| ---- | ---- |
| ymd | string |
| dividend | float |
| exchange | string |
| symbol | string |

参考代码（Hive QL）：
```sql
create external table if not exists dividends_unpartitioned
(
    `exchange` string ,
    `symbol` string,
    `ymd` string,
    `dividend` float
)
row format delimited fields terminated by ',';
load data local inpath '/home/hadoop/data/dividends/dividends.csv' overwrite into table dividends_unpartitioned;
```

### 5. 利用 Hive 自动分区特性向分区表插入数据

以针对 dividends_unpartitioned 的查询为基础，利用 Hive 自动分区特性向分区表 dividends 各个分区中插入对应数据。

参考代码（Hive QL）：
```sql
set hive.exec.dynamic.partition=true;
set hive.exec.dynamic.partition.mode=nonstrict;
set hive.exec.max.dynamic.partitions.pernode=1000;
insert overwrite table dividends partition(`exchange`,`symbol`) select `ymd`,`dividend`,`exchange`,`symbol` from dividends_unpartitioned;
```

### 6. Hive 表连接查询

查询 IBM （ symbol = IBM ）从 2000 年起所有支付股息的交易日（ dividends 表中有对应记录）的收盘价（ price_close ）。

参考代码（Hive QL）：
```sql
select s.ymd,s.symbol,s.price_close
from stocks s LEFT SEMI JOIN dividends d
ON s.ymd=d.ymd and s.symbol=d.symbol
where s.symbol='IBM' and year(ymd)>=2000;
```

### 7. Hive 查询输出结果自定义映射

查询苹果公司（ symbol = AAPL ） 2008 年 10 月每个交易日的涨跌情况，涨显示 rise ，跌显示 fall ，不变显示 unchange 。

参考代码（Hive QL）：
```sql
select ymd,
case
    when price_close-price_open>0 then 'rise'
    when price_close-price_open<0 then 'fall'
    else 'unchanged'
end as situation
from stocks
where symbol='AAPL' and substring(ymd,0,7)='2008-10';
```


### 8. Hive 数据表排序并限制输出结果条数

查询 stocks 表中收盘价（ price_close ）比开盘价（ price_open ）高得最多的那条记录的交易所（ exchange ）、股票代码（ symbol ）、日期（ ymd ）、收盘价、开盘价及二者差价。

参考代码（Hive QL）：
```sql
select `exchange`,symbol,ymd,price_close-price_open as `diff`
from
(
    select *
    from stocks
    order by price_close-price_open desc
    limit 1
) t;
```

### 9. Hive 表数据聚合查询

从 stocks 表中查询苹果公司（ symbol = AAPL ）年平均调整后收盘价（ price_adj_close ）大于 50 美元（约 352.51 元）的年份及年平均调整后收盘价。

参考代码（Hive QL）：
```sql
select
    year(ymd) as `year`,
    avg(price_adj_close) as avg_price from stocks
where `exchange`='NASDAQ' and symbol='AAPL'
group by year(ymd)
having avg_price > 50;
```


### 10. Hive 表嵌套查询

查询每年年平均调整后收盘价（ price_adj_close ）前三名的公司的股票代码及年平均调整后收盘价。

参考代码（Hive QL）：
```sql
select t2.`year`,symbol,t2.avg_price
from
(
    select
        *, row_number() over(partition by t1.`year` order by t1.avg_price desc) as `rank`
    from
    (
        select
            year(ymd) as `year`,
            symbol,
            avg(price_adj_close) as avg_price
        from stocks
        group by year(ymd),symbol
    ) t1
) t2
where t2.`rank` <= 3;
```