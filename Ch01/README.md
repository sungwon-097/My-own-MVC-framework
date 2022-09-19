-   강의 소개

### 강의 목표

1. 객체지향의 이해
2. HTTP 프로토콜 및 HTTP 웹 서버 동작 원리 이해
3. MVC 구조 및 DI 내부 동작 원리 이해

### 개발환경 구축

-   java SDK(강의에선 11버전 사용)

```c
# java -version
java version "17.0.1" 2021-10-19 LTS
Java(TM) SE Runtime Environment (build 17.0.1+12-LTS-39)
Java HotSpot(TM) 64-Bit Server VM (build 17.0.1+12-LTS-39, mixed mode, sharing)
```

-   intelliJ IDEA
-   Apache Tomcat

```c
# cd apache-tomcat-9.0.65/bin
# ./startup.sh
...
Tomcat started.
/*

Tomcat은 Servlet Interface 구현체이며 Servlet Container중 하나

- Servlet : 클라이언트의 요청이 오면 기능을 수행하고 그 결과를 웹 브라우저에 돌려줌
    - Servlet Interface : Servlet관련 추상 메서드를 선언(init(), service()등)
    - Setvlet Container : Servlet의 라이프사이클을 관리(Clinet의 Request를 받아주고 Response할 수 있게, 웹 서버와 소켓을 만들어 통신함)

*/
```
