# 实验目的
- 通过实验掌握基本的 MapReduce 编程方法。
- 掌握用 MapReduce 解决一些常见数据处理问题的方法，包括数据合并、数据去重、数据排序和数据挖掘等。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Hadoop 版本：3.3.5
- Java IDE：Visual Studio Code

# 实验内容和要求
### 一、编程实现文件合并和去重操作
对于两个输入文件，即文件 A 和文件 B ，请编写 MapReduce 程序，对两个文件进行合并，并剔除其中重复的内容，得到一个新的输出文件 C 。下面是输入文件和输出文件的一个样例，以供参考。

输入文件 A 的样例如下：

```
20210501 x
20210502 y
20210503 x
20210504 y
20210505 z
20210506 x
```

输入文件 B 的样例如下：

```
20210501 y
20210502 y
20210503 x
20210504 z
20210505 y
```

根据输入文件 A 和 B 合并得到的输出文件 C 的样例如下：

```
20210501 x
20210501 y
20210502 y
20210503 x
20210504 y
20210504 z
20210505 z
20210505 y
20210506 x
```

### 二、编程实现对输入文件的排序

现在有多个输入文件，每个文件中的每行内容均为一个整数。要求读取所有文件中的整数，进行升序排列后，将 其输出到一个新文件中，输出的数据格式为每行两个整数，第一个整数为第二个整数的排序位次，第二个整数为 原待排列的整数。下面是输入文件和输出文件的一个样例，以供参考。 

输入文件 1 的样例如下：

```
33
37
12
40
```

输入文件 2 的样例如下：

```
4
16
39
5
```

输入文件 3 的样例如下：

```
1
45
25
```

根据输入文件 1 、 2 和 3 得到的输出文件如下：

```
1 1
2 4
3 5
4 12
5 16
6 25
7 33
8 37
9 39
10 40
11 45
```

### 三、对给定的表格信息进行信息挖掘

下面给出一个 child-parent 的表格，要求挖掘其中的父子关系，给出祖孙关系的表格。 输入文件内容如下：

```
child parent
Steven Lucy
Steven Jack
Jone Lucy
Jone Jack
Lucy Mary
Lucy Frank
Jack Alice
Jack Jesse
David Alice
David Jesse
Philip David
Philip Alma
Mark David
Mark Alma
```

输出文件内容如下：

```
grandchild grandparent
Steven Alice
Steven Jesse
Jone Alice
Jone Jesse
Steven Mary
Steven Frank
Jone Mary
Jone Frank
Philip Alice
Philip Jesse
Mark Alice
Mark Jesse
```