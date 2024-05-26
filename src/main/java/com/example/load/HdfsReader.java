package com.example.load;

import com.example.processor.HdfsProcessor;

import java.io.IOException;

public class HdfsReader {
    public static void main(String[] args) throws IOException {
        HdfsProcessor hdfsProcessor = new HdfsProcessor();
        hdfsProcessor.readHdfs("temp");

    }
}
