package com.time1043;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.functions.KeySelector;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCountStreamDemo {
    public static void main(String[] args) throws Exception {
        // 基于DataStream API
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();  // 创建执行环境
        DataStreamSource<String> lineDSS = env.readTextFile("input/A Tale of Two Cities.txt");

        // 处理数据：按行按空格切分、map、reduce
        SingleOutputStreamOperator<Tuple2<String, Integer>> sumDS = lineDSS.flatMap(new FlatMapFunction<String, Tuple2<String, Integer>>() {  // 输入 输出
            @Override
            public void flatMap(String value, Collector<Tuple2<String, Integer>> out) throws Exception {
                String[] words = value.split(" ");
                for (String word : words) {
                    Tuple2<String, Integer> wordTuple2 = Tuple2.of(word, 1);
                    out.collect(wordTuple2);  // (word, 1)
                }
            }
        }).keyBy(new KeySelector<Tuple2<String, Integer>, String>() {  // 输入数据 key
            @Override
            public String getKey(Tuple2<String, Integer> value) throws Exception {
                return value.f0;  // 根据二元组的第一个元素 分组
            }
        }).sum(1);// 根据二元组的第二个元素 聚合

        sumDS.print();  // 输出数据
        env.execute();  // 执行

    }
}

/*
    接口A 里面有方法a()

    正常写法：
    定义一个class B  实现接口A  实现方法a()
    B b = new B()

    匿名实现类：
    new A() {
        a() {
        ...
        }
    }
*/

/*
    5> (hello,1)
    5> (hello,2)
    5> (hello,3)
    1> (scala,1)
    3> (java,1)
    13> (flink,1)
    5> (hello,4)
    9> (world,1)
*/