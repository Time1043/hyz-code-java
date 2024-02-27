package com.time1043;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.operators.AggregateOperator;
import org.apache.flink.api.java.operators.DataSource;
import org.apache.flink.api.java.operators.FlatMapOperator;
import org.apache.flink.api.java.operators.UnsortedGrouping;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.util.Collector;

public class WordCountBatchDemo {
    public static void main(String[] args) throws Exception {
        // 基于DataSet API (不推荐)
        ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();  // 创建执行环境
        DataSource<String> lineDS = env.readTextFile("input/word.txt");// 读取数据 (相对于工程根路径)

        // 处理数据：按行按空格切分、map、reduce
        FlatMapOperator<String, Tuple2<String, Integer>> wordAndOne = lineDS.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");  // 一行数据 -> 一个单词
                for (String word : words) {
                    Tuple2<String, Integer> wordTuple2 = Tuple2.of(word, 1);// (word, 1)
                    out.collect(wordTuple2);  // 调用采集器Collector向下游发送数据
                }
            }
        });
        UnsortedGrouping<Tuple2<String, Integer>> wordAndOneGroupBy = wordAndOne.groupBy(0);  // 按照索引为0的元素去分组
        AggregateOperator<Tuple2<String, Integer>> sum = wordAndOneGroupBy.sum(1);  // 按照索引为1的元素去聚合

        sum.print();  // 输出
    }
}

/*
    (scala,1)
    (flink,1)
    (world,1)
    (hello,4)
    (java,1)
*/