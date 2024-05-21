package com.example;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.hadoop.util.Progressable;

import java.io.*;
import java.net.URI;

public class Local2HDFS {
    public Local2HDFS() {
        fredProp = PropertyFileReader.readPropertyFile("SystemConfig.properties");
        String HADOOP_CONF_DIR = fredProp.getProperty("hadoop.conf.dir");

        mapper = new ObjectMapper();

        Configuration conf = new Configuration();
        conf.addResource(new Path("file:///" + HADOOP_CONF_DIR + "/core-site.xml"));
        conf.addResource(new Path("file:///" + HADOOP_CONF_DIR + "/hdfs-site.xml"));

        String namenode = fredProp.getProperty("hdfs.namenode.url");
        hadoopFs = FileSystem.get(new URI(namenode), conf);
    }

    public void writeToHdfs(String localSrc, String dst) throws IOException {

        InputStream in = new BufferedInputStream(new FileInputStream(localSrc));

        Configuration conf = new Configuration();
        FileSystem fs = FileSystem.get(URI.create(dst), conf);
        OutputStream out = fs.create(new Path(dst), new Progressable() {
            public void progress() {
                System.out.print(".");
            }
        });

        IOUtils.copyBytes(in, out, 4096, true);
    }
}
