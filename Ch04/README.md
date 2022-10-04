-   웹 어플리케이션의 이해

### 웹 어플리케이션 프로그램으로 구현하기

1. 사용자 요청을 메인쓰레드가 처리하도록 한다
2. 사용자 요청이 들어 올 때마다 쓰레드를 새로 생성해서 사용자 요청을 처리 하도록 한다
3. 쓰레드풀을 적용해 안정적인 서비스가 가능하도록 한다

-   HTTP
    -   서버와 클라이언트가 데이터를 주고받기 위한 규약
    -   HTTP/1.1, 2는 TCP 기반 위에서 동작(3-way handshaking(\*)을 통해 연결을 함)
    -   HTTP/3은 UDP기반으로 3-way handshaking이 필요하지 않음
    -   무상태 프로토콜(Stateless)
        -   서버가 클라이언트 상태를 유지하지 않아 Keep Alive 속성을 사용
    -   비연결성(Connectionless)
        -   응답을 마치면 연결을 끊음. 쿠키, 세션, jwt등에 정보 저장
    -   요청 메소드
        -   GET, POST, PUT, DELETE, ...
        -   200 Success, 300 Redirection, 400 ClientError, 500 InternalServerError
    -   Header
        -   Content-type, Accept, Cookie, Set-Cookie, Authorization

(\*) 3-Way Handshake : 응용프로그램이 데이터를 전송하기 전에 먼저 정확한 전송을 보장하기 위해 상대방 컴퓨터와 사전에 세션을 수립하는 과정을 의미

Client -> Server : TCP SYN
Server -> Client : TCP SYN ACK
Client -> Server : TCP ACK

| 클라이언트    |     |     |     | 서버          |
| ------------- | --- | --- | --- | ------------- |
| (요청 메시지) | --> |     |     | Status line   |
| Request line  |     |     |     | Header        |
| Header        |     |     |     | Blank line    |
| Blank line    |     |     |     | Body          |
| Body          |     |     | <-- | (응답 메시지) |

### Step 1

```java
try(InputStream in = clientSocket.getInputStream();
    OutputStream out = clientSocket.getOutputStream()){
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
        // Line by line 으로 읽기 위해 InputStream 을 BufferedReader 로 변환
        DataOutputStream dos = new DataOutputStream(out);

        String line;
        while((line = br.readLine()) != ""){
            System.out.println(line);
        }
    }

17:06:28.866 [main] INFO com.example.waspractice.CustomWebApplicationServer - [CustomWebApplicationServer] client connected.
GET / HTTP/1.1
Host: localhost:8080
Connection: Keep-Alive
User-Agent: Apache-HttpClient/4.5.13 (Java/11.0.14.1)
Accept-Encoding: gzip,deflate

>> 이러한 Protocol을 판단해 spring으로 보내는 역할을 Tomcat에서 수행함
```

### Step 2

-   새로운 요청이 들어 올 때 마다 새로운 Thread를 생성하여 처리
    1. 장점 : 동시성 향상
    2. 단점 : 메모리와 프로세서 사용량이 증가
       -new Thread().start() 는 각각의 메모리 공간(stack)을 할당하며 프로세서에서 Context Switch 하는 횟수 또한 많아지기 때문
    3. 해결책 : Thread pool을 적용한다

### Step 3

-   new Thread 를 무제한으로 실행 하게 되면 자원이 부족해 시스템이 다운 될 수 있다는 문제점이 발생한다.
-   Thread Pool은 작업 처리에 사용되는 스레드를 제한된 개수만큼 정해 놓고 작업 큐에 들어오는 작업들을 하나씩 스레드가 맡아 처리한다.

```java
// CustomWebApplicationServer
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {
  private final ExecutorService executorService = Executors.newFixedThreadPool(10); // Thread 10개를 운용하는 Pool

//   new Thread(new ClientRequestHandler(clientSocket)).start();
  executorService.execute(new ClientRequestHandler(clientSocket));
}
```

### CGI(Common Gateway Interface)

-   웹 서버와 어플리케이션 사이에 데이터를 주고 받는 규약
-   CGI 규칙에 따라서 만들어진 프로그램을 CGI프로그램이라고 함
-   컴파일 방식과 인터프리터 방식이 있음
    -   인터프리터 방식 : 웹서버 <-> 스크립트 엔진 <-> 스크립트 파일
    -   서블릿 컨테이너 : 웹서버 <-> 서블릿 컨테이너 <-> 서블릿 파일

### Servlet

-   자바에서 웹어플리케이션을 만드는 기술
-   자바에서 동적인 웹 페이지를 구현하기 위한 표준

### Servlet Container

-   Container는 라이프사이클을 관리한다는 의미를 가짐, 서블릿의 생성부터 소멸까지 관지하는 역할을 한다
-   웹서버와 소켓을 만들고 통신하는 과정을 대신 처리해준다 -> 비즈니스 로직에만 집중 할 수 있게 해준다
-   싱클톤으로 서블릿 객체를 관리한다(인스턴스 하나만 생성하여 공유)
    -   stateful 하게 설계하면 안된다 -> Thread safeful 하지 않기 때문

### WAS

-   WAS ⊃ Servlet Container (포함관계)
-   WAS는 매 요청마다 스레드풀에서 기존 스레드를 사용한다
-   주요 튜닝 포인트는 Number of Max Thread
-   대표적으로 톰캣이 있다

### 서블릿 프로그래밍

|                                |     |                |
| ------------------------------ | --- | -------------- |
| Javax.servlet.Servlet          |     | interface      |
| ↑                              |     |                |
| Javax.servlet.GenericServlet   |     | abstract class |
| ↑                              |     |                |
| Javax.servlet.http.HttpServlet |     | abstract class |
| ↑                              |     |                |
| UserDefinedServlet             |     | concrete class |

-   ServletInterface

    -   서블릿 컨테이너가 서블릿 인터페이스에 있는 메소드들을 호출함
    -   생명주기와 관련된 메소드
        1. init() : 서블릿 생성 후 초기화 작업을 수행하기 위해 호출
        2. service() : client 요청이 들어 올 때마다 호출
        3. distroy() : 종료 될 때 호출
    -   기타 메소드
        1. getServletConfig() : 서블릿 초기 설정 정보를 가지고 있는 객체를 반환. 서블릿 이름과 초기 매개변수 이름을 얻을 수 있음
        2. getServletInfo() : 서블릿을 작성 한 사람, 버전, 저작권 등의 서블릿 정보를 반환함

-   (\*) SpringBoot에서 서블릿을 관리 해 주는데 서블릿이 어떻게 이루어져있고 어떻게 동작하는지 왜 알아야 하지?
    -   DispatcherServlet의 플로우를 이해해야 스프링이 어떻게 동작 하는지 알 수 있음
    -   DispatcherServlet의 : HTTP 프로토콜로 들어오는 모든 요청을 가장 먼저 받아 적합한 컨트롤러에 위임해주는 프론트 컨트롤러
    -   해당 어플리케이션으로 들어오는 모든 요청을 핸들링해주고 공통 작업을 처리
