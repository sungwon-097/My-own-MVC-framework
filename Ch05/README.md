-   JDBC

### JDBC란?

-   자바 애플리케이션에서 DB프로그래밍을 할 수 있도록 도와주는 표준 인터페이스
-   인턴페이스 구현체들은 각 DB 벤더사들이 제공해주며 JDBC Driver라고 함
-   [실습코드](/src/jdbc-practice/)

### DBCP(DB 커넥션 풀)

-   미리 일정량의 커넥션을 생성해서 풀에 저장 해 두고 있다가 HTTP요청에 따라 필요할 때 풀에서 커넥션을 가져다 쓰는 기법
-   SpringBoot 2.0에서는 HikariCP 라이브러리를 디폴트로 사용

    -   Tomcat JDBC, Apache Commons DBCP등도 있음

-   유의사항
    1. 커넥션의 사용 주체는 was스레드이므로 커넥션 개수는 was스레드 수와 함께 고려함
    2. 커넥션 수를 크게 설정하면 메모리 소모가 큰 대신 동접자수가 많아지더라도 사용자 대기 시간이 상대적으로 줄어들고, 그 반대면 메모리 소모는 작은 대신 대기시간이 길어 질 수 있음. 적정량 생성 필요

### DataSource

-   커넥션을 획득 하기 위한 표준 인터페이스
-   HikariCP의 Datasource를 사용한다
-   MAX_POOL_SIZE는 40
