#!/bin/sh
ssh -o StrictHostKeyChecking=no namenode1 "zkServer.sh start"
ssh -o StrictHostKeyChecking=no namenode2 "zkServer.sh start"
ssh -o StrictHostKeyChecking=no resourcemanager "zkServer.sh start"
ssh namenode1 start-dfs.sh
ssh resourcemanager start-yarn.sh

