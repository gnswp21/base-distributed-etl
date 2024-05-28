package com.example.load;

import com.example.processor.SparkProcessor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.streaming.StreamingQuery;
import org.apache.spark.sql.streaming.StreamingQueryException;

import java.util.concurrent.TimeoutException;

public class Tester {
    public static void main(String[] args) {
        // create spark session master:yarn, appNmae:test
        SparkProcessor sp = new SparkProcessor("test");

        // read dataframe from HDFS
        Dataset<Row> df = sp.readDF("hdfs://mycluster/data/webtoon-data.csv");

        // svae DF to hdfs
        sp.saveDF2Hdfs(df);

        // send DF to kafka brokers as topic
        sp.sendDF2Kafka(df);
        // stop spark session
        sp.stopSparkSession();

    }
}
