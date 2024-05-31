package com.example.processor;

import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.functions;

public class SparkProcessor {
    SparkSession spark;
    public SparkProcessor(String appName) {
        this.spark = SparkSession.builder()
                .master("yarn").appName(appName).getOrCreate();
    }

    public SparkSession getSparkSession() {
        return spark;
    }

    public void stopSparkSession(){
        spark.stop();
    }

    public Dataset<Row> readDF(String filePath){
        // String filePath = "hdfs://mycluster/data/webtoon-data.csv";
        Dataset<Row> df = spark.read().format("csv")
                .option("header", "true")
                .option("inferSchema", "true")
                .load(filePath);
        // 데이터프레임의 상위 5개 행 출력
        df.show(5);

    return df;
    }

    public void sendDF2Kafka(Dataset<Row> df){
        // 카프카로 전송
        Dataset<Row> jsonDF = df.selectExpr("CAST(id AS STRING) as key", "to_json(struct(*)) AS value");

        try {
            jsonDF
                    .write()
                    .format("kafka")
                    .option("kafka.bootstrap.servers", "mykafka1:9092, mykafka2:9092, mykafka3:9092")
                    .option("topic", "webtoon-topic")
                    .option("checkpointLocation", "/tmp/kafka-checkpoints")
                    .save();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public void saveDF2Hdfs(Dataset<Row> df, String filename){
        df.write().format("csv").mode("overwrite").option("sep", "\t")
                .save("/data/" + filename);
    }

    public  Dataset<Row> preprocessDf(Dataset<Row> df){

        // 1. 제목(Name) 결측치 찾기
        Dataset<Row> missingTitles = df.filter(functions.col("Name").isNull());
        missingTitles.show();

        // 2. 제목 길이 새로운 열 추가 및 Name이 null이 아닌 행만 선택
        Dataset<Row> dfWithLength = df.withColumn("titleLength", functions.length(functions.col("Name")))
                .filter(functions.col("Name").isNotNull());

        // "Rating" 열 숫자 형식으로 변환
        Dataset<Row> dfWithNumeric = dfWithLength
                .withColumn("Rating", functions.col("Rating").cast("double"));

        // 필요한 열 선택
        Dataset<Row> selectedColumns = dfWithNumeric.select("id", "titleLength", "Rating");

       return selectedColumns;

    }
}
