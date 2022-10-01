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
