#!/bin/sh

yes | dpkg -i /root/java/"$1"

cd /usr/lib/jvm
jdk_version=$(ls | head -n 1)
ln -s $jdk_version jdk
cat /root/java/java-bashrc.txt >> ~/.bashrc
