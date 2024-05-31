#!/bin/sh
ssh -o StrictHostKeyChecking=no namenode1 "zkServer.sh start && hdfs --daemon  start journalnode"
ssh -o StrictHostKeyChecking=no namenode2 "zkServer.sh start && hdfs --daemon  start journalnode"
ssh -o StrictHostKeyChecking=no resourcemanager "zkServer.sh start && hdfs --daemon  start journalnode"
ssh namenode1 "hdfs namenode -format && hdfs --daemon  start namenode"
ssh namenode2 "hdfs namenode -bootstrapStandby && hdfs --daemon  start namenode"
ssh namenode1 "hdfs zkfc -formatZK"
ssh namenode1 start-dfs.sh
ssh resourcemanager start-yarn.sh


