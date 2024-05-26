Distributed ETL 프로젝트의 개발용 로컬버전

docker compose up -d
docker exec -it namenode1 bash
sh cluster-init.sh

# 기능

# 구조

# 사용법


# info
자세한 구현은 아래 벨로그 글에서 확인해주세요!

# 리모트 디버깅
1. docker cp build/libs/base-distributed-etl-1.0-SNAPSHOT.jar namenode1:/root/app.jar
2. java -agentlib:jdwp=transport=dt_socket,server=y,suspend=y,address=*:5005 -cp app.jar com.example.Main
3. 인텔리제이 리모드 디버그 실행

위의 실행엔 fat-jar 필요

# 하둡 jar로 실행
hadoop jar app.jar com.example.main