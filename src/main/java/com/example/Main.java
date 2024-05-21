package com.example;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Hello world!");
        String data;
        String namenodeURI="/mycluster:9000";

        Local2HDFS localDataWriter = new Local2HDFS();
        data = "data/Webtoon Dataset.csv";
        localDataWriter.writeToHdfs(data, namenodeURI + "/data/webtoon-data.csv");


    }
}