package com.example.load;

import com.example.processor.HdfsProcessor;

import java.io.*;
import java.util.List;

public class DataUploader2Hdfs {
    public static void main(String[] args) throws IOException {
        HdfsProcessor hdfsProcessor = new HdfsProcessor();
        System.out.println("========================== Webtoon Data");
        hdfsProcessor.writeToHdfs("/root/data/Webtoon Dataset.csv", "hdfs://mycluster/data/webtoon-data.csv" );


    }
}
