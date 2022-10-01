package com.example.waspractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private final int port;

    private static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);
    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try(ServerSocket serverSocket = new ServerSocket(port)){
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client.");

            while((clientSocket = serverSocket.accept()) != null){ // 해당 port 로 serverSocket 을 만든 다음에 서버를 기다리게 함
                logger.info("[CustomWebApplicationServer] client connected.");

//                1. 사용자 요청을 메인쓰레드가 처리하도록 한다

                try(InputStream in = clientSocket.getInputStream();
                    OutputStream out = clientSocket.getOutputStream()){
                    BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
//                    Line by line 으로 읽기 위해 InputStream 을 BufferedReader 로 변환
                    DataOutputStream dos = new DataOutputStream(out);

                    String line;
                    while((line = br.readLine()) != ""){
                        System.out.println(line);
                    }
                }
            }
        }
    }
}
