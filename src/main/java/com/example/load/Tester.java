package com.example.load;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

public class Tester {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        SparkSession spark = SparkSession.builder()
                .master("yarn").appName("test").getOrCreate();

        String filePath = "hdfs://mycluster/data/webtoon-data.csv";

        // CSV 파일 읽기
        Dataset<Row> df = spark.read().format("csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .load(filePath);
        // 데이터프레임의 상위 5개 행 출력
        df.show(5);

        // 카프카로 전송
        Dataset<Row> jsonDF = df.selectExpr("CAST(id AS STRING) as key", "to_json(struct(*)) AS value");

        // Kafka로 데이터프레임을 전송
        try {
            StreamingQuery query = jsonDF
                    .writeStream()
                    .format("kafka")
                    .option("kafka.bootstrap.servers", "localhost:9092")
                    .option("topic", "webtoon-topic")
                    .option("checkpointLocation", "/tmp/kafka-checkpoints")
                    .start();

            query.awaitTermination();
        } catch (StreamingQueryException | TimeoutException e) {
            e.printStackTrace();
        }



//
//        // 디렉터리로 파일로 저장
//        df.write().format("csv").mode("overwrite").option("sep", "\t")
//                .save("/data/webtoon-data-copy.csv");

//        //// 디렉토리에서 CSV 파일 읽기
//                String readPath = "hdfs://mycluster/data/webtoon-data-copy.csv";
//                Dataset<Row> df2 = spark.read()
//                    .format("csv")
//                    .option("header", "true")
//                    .option("inferSchema", "true")
//                    .option("sep", "\t")
//                    .load(readPath);
//
//        df2.show(5);
        // 세션 종료
        spark.stop();

    }
}
