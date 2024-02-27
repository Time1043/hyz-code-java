package com.time1043;

import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.util.Collector;

public class WordCountStreamUnboundedDemo {
    public static void main(String[] args) throws Exception {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> socketDS = env.socketTextStream("ctbase", 7777);

        SingleOutputStreamOperator<Tuple2<String, Integer>> sumDS = socketDS
                .flatMap((String value, Collector<Tuple2<String, Integer>> out) -> {
                    String[] words = value.split(" ");
                    for (String word : words) {
                        out.collect(Tuple2.of(word, 1));  // (word, 1)
                    }
                }).returns(Types.TUPLE(Types.STRING, Types.INT))  // java lambda 类型擦除 需明确指定
                .keyBy((Tuple2<String, Integer> value) -> {
                    return value.f0;
                })
                .sum(1);

        sumDS.print();
        env.execute();

        // flink：有数据来才输出 (事件驱动型)
        // spark streaming：每5s输出一个结果
    }
}
