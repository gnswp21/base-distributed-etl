#!/bin/sh
ssh namenode1 "zkServer.sh start"
ssh namenode2 "zkServer.sh start"
ssh resourcemanager "zkServer.sh start"
ssh namenode1 start-all.sh

