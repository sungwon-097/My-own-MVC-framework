-   개발 환경 구성하기

### Docker

-   컨테이너 기반의 가상화 플랫폼( <-> 하이퍼바이저 기반의 가상화 또는 OS 가상화)
-   Docker hub : 도커에서 제공하는 이미지 저장소(https://hub.docker.com/)
-   Docker Compose : 다중 컨테이너를 정의하고 실행하기 위한 도구, \*.yaml 파일을 사용해 다중 컨테이너 구성

### MySQL image

```c
# docker -v
Docker version 20.10.12, build e91ed57

# docker pull mysql

# docker run --name mysql-sample-container -e MYSQL_ROOT_PASSWORD=<password> -d -p 3306:3306 mysql:{version}
ysql-sample-container -e MYSQL_ROOT_PASSWORD=test -d -p 3306:3306 mysql:latest
84634eb9194268de583af40f86370165c127bd61e11527c2f6ec0276815eb190
// 포트포워딩 후 패스워드 설정하고 최신버전 설치

# docker ps -a
CONTAINER ID   IMAGE          COMMAND                  CREATED          STATUS         PORTS                               NAMES
84634eb91942   mysql:latest   "docker-entrypoint.s…"   10 seconds ago   Up 9 seconds   0.0.0.0:3306->3306/tcp, 33060/tcp   mysql-sample-container

# docker exec -it {container name} bash
// mysql 실행

bash-4.4# mysql -u root -p
// root라는 계정 이름으로 password를 요청
Enter password:

mysql>
```
