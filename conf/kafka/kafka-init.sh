# For zookeeper1
ssh mykafka1 echo '"brokers.id=1" >> /root/kafka/config/server.properties'
ssh mykafka1 mkdir -p /var/lib/zookeeper
ssh mykafka1 echo '"1" > /var/lib/zookeeper/myid'
# For zookeeper2
ssh mykafka2 echo '"brokers.id=2" >> /root/kafka/config/server.properties'
ssh mykafka2 mkdir -p /var/lib/zookeeper
ssh mykafka2 echo '"2" > /var/lib/zookeeper/myid'

# For zookeeper3
ssh mykafka3 echo '"brokers.id=3" >> /root/kafka/config/server.properties'
ssh mykafka3 mkdir -p /var/lib/zookeeper
ssh mykafka3 echo '"3" > /var/lib/zookeeper/myid'

# run
ssh mykafka1 "sh /root/kafka/bin/zookeeper-server-start.sh -daemon /root/kafka/config/zookeeper.properties"
ssh mykafka2 "sh /root/kafka/bin/zookeeper-server-start.sh -daemon /root/kafka/config/zookeeper.properties"
ssh mykafka3 "sh /root/kafka/bin/zookeeper-server-start.sh -daemon /root/kafka/config/zookeeper.properties"

ssh mykafka1 "sh /root/kafka/bin/kafka-server-start.sh -daemon /root/kafka/config/server.properties"
ssh mykafka2 "sh /root/kafka/bin/kafka-server-start.sh -daemon /root/kafka/config/server.properties"
ssh mykafka3 "sh /root/kafka/bin/kafka-server-start.sh -daemon /root/kafka/config/server.properties"
