#!/bin/sh
ssh namenode1 "zkServer.sh start && hdfs --daemon  start journalnode"
ssh namenode2 "zkServer.sh start && hdfs --daemon  start journalnode"
ssh resourcemanager "zkServer.sh start && hdfs --daemon  start journalnode"
ssh namenode1 "hdfs namenode -format && hdfs --daemon  start namenode"
ssh namenode2 "hdfs namenode -bootstrapStandby && hdfs --daemon  start namenode"
ssh namenode1 "hdfs zkfc -formatZK"
ssh namenode1 start-all.sh

