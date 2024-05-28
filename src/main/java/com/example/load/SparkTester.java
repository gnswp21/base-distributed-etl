package com.example.load;

import com.example.processor.SparkProcessor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SparkTester {
    public static void main(String[] args) {
        // create spark session master:yarn, appNmae:test
        SparkProcessor sp = new SparkProcessor("test");

        // read dataframe from HDFS
        Dataset<Row> df = sp.readDF("hdfs://mycluster/data/webtoon-data.csv");

        // save DF to hdfs
        sp.saveDF2Hdfs(df);

        // send DF to kafka brokers as topic
        sp.sendDF2Kafka(df);
        // stop spark session
        sp.stopSparkSession();

    }
}
