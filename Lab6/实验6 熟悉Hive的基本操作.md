# 实验目的
- 理解Hive作为数据仓库在Hadoop体系结构中的角色。
- 熟练使用常用的HiveQL语句。

# 实验平台
- 操作系统：Ubuntu
- JDK 版本：JDK 1.8
- Hadoop 版本：3.3.5
- Hive 版本：3.1.3

# 实验内容和要求
### 0. 准备数据
使用 MobaXterm 将 `prog-hive-1st-ed-data.zip` 上传至 `/tmp` 目录下

安装 unzip ：

```bash
sudo apt install unzip
```

解压数据：

```bash
cd /tmp
unzip prog-hive-1st-ed-data.zip
```


### 1. 创建内部表 stocks 并导入数据
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
load data local inpath '/tmp/data/stocks/stocks.csv' overwrite into table stocks;
```


### 2. 创建内部表 dividends 并导入数据

创建一个内部表 dividends，字段分隔符为英文逗号，表结构如表 2 所示：

> 表 2 dividends 表结构 

|col_name|data_type|
| ---- | ---- |
| ymd | string |
| dividend | float |
| exchange | string |
| symbol | string |

参考代码（Hive QL）：
```sql
create table if not exists dividends
(
    `exchange` string,
    `symbol` string,
    `ymd` string,
    `dividend` float
)
row format delimited fields terminated by ',';
load data local inpath '/tmp/data/dividends/dividends.csv' overwrite into table dividends;
```


### 3. Hive 表连接查询

查询 IBM （ symbol = IBM ）从 2000 年起所有支付股息的交易日（ dividends 表中有对应记录）的收盘价（ price_close ）。

参考代码（Hive QL）：
```sql
select s.ymd,s.symbol,s.price_close
from stocks s LEFT SEMI JOIN dividends d
ON s.ymd=d.ymd and s.symbol=d.symbol
where s.symbol='IBM' and year(ymd)>=2000;
```

### 4. Hive 查询输出结果自定义映射

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


### 5. Hive 数据表排序并限制输出结果条数

查询 stocks 表中收盘价（ price_close ）比开盘价（ price_open ）高得最多的那条记录的交易所（ exchange ）、股票代码（ symbol ）、日期（ ymd ）、收盘价、开盘价及二者差价。

参考代码（Hive QL）：
```sql
select `exchange`,`symbol`,`ymd`,`price_close`-`price_open` as `diff`
from
(
    select *
    from stocks
    order by `price_close`-`price_open` desc
    limit 1
) t;
```

### 6. Hive 表数据聚合查询

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


### 7. Hive 表嵌套查询

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