package com.example.processor;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class HdfsProcessor {
    private final FileSystem fs;
    public HdfsProcessor() throws IOException {
        String namenodeURI="hdfs://mycluster";
        Configuration conf = new Configuration();
        conf.addResource(new Path("/root/hadoop/etc/hadoop/core-site.xml"));
        conf.addResource(new Path("/root/hadoop/etc/hadoop/hdfs-site.xml"));
        this.fs = FileSystem.get(URI.create(namenodeURI), conf);

    }

    public void writeToHdfs(String localSrc, String dst) throws IOException {

        InputStream in = null;
        OutputStream out = null;
        try {
            File localFile = new File(localSrc);
            if (!localFile.exists()) {
                System.err.println("Local source file does not exist: " + localSrc);
                return;
            }

            in = new BufferedInputStream(new FileInputStream(localSrc));
            System.out.println("Input stream opened successfully.");

            out = fs.create(new Path(dst), new Progressable() {
                public void progress() {
                    System.out.print(".");
                }
            });

            System.out.println("Starting to copy bytes...");
            IOUtils.copyBytes(in, out, 4096, true);
            System.out.println("Finished copying bytes.");

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeStream(in);
            IOUtils.closeStream(out);
            System.out.println("Streams closed.");
        }
    }

    public void readHdfs(String filename) throws IOException {
        Path filePath = new Path("/" + filename);
        InputStream in = null;
        try {
            in = fs.open(filePath);
            IOUtils.copyBytes(in, System.out, 4096, false);
        } finally {
            IOUtils.closeStream(in);
        }
    }
}
