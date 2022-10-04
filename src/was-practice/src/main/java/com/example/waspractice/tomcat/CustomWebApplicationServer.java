package com.example.waspractice.tomcat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    private final ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while((clientSocket = serverSocket.accept()) != null){ // 해당 port 로 serverSocket 을 만든 다음에 서버를 기다리게 함
                logger.info("[CustomWebApplicationServer] client connected.");

//              Step 2. 사용자 요청이 새로 들어 올 때 마다 Thread 를 따로 생성해서 사용자 요청을 처리하도록 한다.
//              clientSocket 을 통해 I/O stream 을 진행
//                new Thread(new ClientRequestHandler(clientSocket)).start();

//              Step 3. Thread Pool 을 적용
                executorService.execute(new ClientRequestHandler(clientSocket));

//              Step 1. 사용자 요청을 메인쓰레드가 처리하도록 한다
//                try(InputStream in = clientSocket.getInputStream();
//                    OutputStream out = clientSocket.getOutputStream()){
//                        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//    //                    Line by line 으로 읽기 위해 InputStream 을 BufferedReader 로 변환
//                        DataOutputStream dos = new DataOutputStream(out);
//
//                        HttpRequest httpRequest = new HttpRequest(br);
//                        // GET /calculate?operand1=11&operand2=55 HTTP/1.1
//                        if(httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")){
//                            QueryStrings queryStrings = httpRequest.getQueryStrings();
//
//                            int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
//                            String operator = queryStrings.getValue("operator");
//                            int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));
//
//                            int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
//                            byte[] body = String.valueOf(result).getBytes();
//                            HttpResponse response = new HttpResponse(dos);
//                            response.response200Header("application/json", body.length);
//                            response.responseBody(body);
//                        }
//                    }
            }
        }
    }
}
