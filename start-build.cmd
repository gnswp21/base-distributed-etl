@echo off
echo Building Docker images...

docker build -t myjava -f java/Dockerfile-java java
echo Built myjava image.

docker build -t myssh -f ssh/Dockerfile-ssh ssh
echo Built myssh image.

docker build -t myhadoop -f hadoop/Dockerfile-hadoop hadoop
echo Built myhadoop image.

docker build -t myhahadoop -f ha/Dockerfile-ha ha
echo Built myhahadoop image.

docker build -t namenode1 -f namenodes/Dockerfile-namenode1 namenodes
echo Built namenode1 image.

docker build -t namenode2 -f namenodes/Dockerfile-namenode2 namenodes
echo Built namenode2 image.

docker build -t resourcemanager -f resourcemanager/Dockerfile-resourcemanager resourcemanager
echo Built resourcemanager image.

docker build -t datanode -f datanode/Dockerfile-datanode datanode
echo Built datanode image.

docker build -t mykafka .
echo Built datanode image.

echo All Docker images have been built successfully.