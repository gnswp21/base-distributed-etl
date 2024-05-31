package com.example.load;

import com.example.processor.SparkProcessor;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

public class SparkTester {
    public static void main(String[] args) {
        // create spark session master:yarn, appNmae:test
        SparkProcessor sp = new SparkProcessor("preprocess");

        // read dataframe from HDFS
        Dataset<Row> df = sp.readDF("hdfs://mycluster/data/webtoon-data.csv");

        // preprocess df
        Dataset<Row> pre_df = sp.preprocessDf(df);

        // save DF to hdfs
        sp.saveDF2Hdfs(pre_df, "preprocessed-webtoon-data.csv");

        // send DF to kafka brokers as topic
        sp.sendDF2Kafka(pre_df);
        // stop spark session
        sp.stopSparkSession();

    }
}
