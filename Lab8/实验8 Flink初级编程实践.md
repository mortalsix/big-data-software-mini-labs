# 实验目的
- 掌握基本的 Flink 编程方法。

# 实验平台
- 操作系统：Ubuntu 22.04
- JDK 版本：JDK 1.8.0_162
- Flink 版本：1.16.2

# 实验内容和要求
### 1. Flink 上的 WordCount 程序

使用 IDE 开发 WordCount 程序，打包成 JAR 文件，提交到 Flink 中运行。

具体过程参考文档： [在Linux中进行Flink应用编程](./在Linux中进行Flink应用编程.md)

### 2. 数据流词频统计

使用 Linux 操作系统自带的 NC 程序模拟生成数据流，不断产生单词并发送出去。编写 Flink 程序对 NC 程序发来的单词进行实时处理，计算词频，并输出词频统计结果。要求在 IDE 中开发和调试程序，然后打包成 JAR 包部署到 Flink 中运行。

参考： https://nightlies.apache.org/flink/flink-docs-release-1.15/docs/dev/datastream/overview/#example-program
