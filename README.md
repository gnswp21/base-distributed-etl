
Distributed ETL 프로젝트의 개발용 로컬버전

아래 벨로그글에 프로젝트에 대한 자세한 설명이 되어 있습니다.
https://velog.io/@gnswp21/%ED%95%98%EB%91%A1-HA%EA%B3%A0%EA%B0%80%EC%9A%A9%EC%84%B1-%EA%B5%AC%EC%84%B1-%EC%9C%88%EB%8F%84%EC%9A%B0-%ED%98%B8%EC%8A%A4%ED%8A%B8-%EB%A1%9C%EC%BB%AC-%EB%8F%84%EC%BB%A4-%EC%BB%A8%ED%85%8C%EC%9D%B4%EB%84%88-%EB%B6%84%EC%82%B0-%ED%81%B4%EB%9F%AC%EC%8A%A4%ED%84%B0

## 실행
1. `.\start-build.cmd`
2. `docker-compose up -d`
3. `docker exec -it namenode1 bash`
4. `sh cluster-init.sh`


###깃허브에 없으므로 다운 받아야 하는 파일
1. java/jdk-11.0.10_linux-x64_bin.deb
2. ha/apache-zookeeper-3.8.4.tar.gz
