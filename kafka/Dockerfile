FROM myssh

WORKDIR /root
RUN apt -y install curl
RUN curl https://archive.apache.org/dist/kafka/2.5.0/kafka_2.12-2.5.0.tgz --output kafka.tgz
RUN tar -xvf kafka.tgz
RUN ln -s kafka_2.12-2.5.0 kafka
RUN apt-get update
RUN apt-get install -y netcat


COPY server.properties /root/kafka/config/server.properties
COPY zookeeper.properties /root/config/kafka/zookeeper.properties
COPY kafka-init.sh /root/kafka/kafka-ids.sh

WORKDIR /root/kafka