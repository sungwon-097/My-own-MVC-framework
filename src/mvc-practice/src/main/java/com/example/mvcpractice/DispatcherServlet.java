package com.example.mvcpractice;

import com.example.mvcpractice.mvc.controller.Controller;
import com.example.mvcpractice.mvc.controller.RequestMappingHandlerMapping;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/") // 모든 요청이 DispatcherServlet 을 거쳐감
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private RequestMappingHandlerMapping requestMappingHandlerMapping;

    @Override
    public void init() throws ServletException { // tomcat 이 servlet 을 싱글톤으로 관리하면서 하나의 객체로 만들 때 init()이 실행됨
        requestMappingHandlerMapping = new RequestMappingHandlerMapping();
        requestMappingHandlerMapping.init();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started");
        try{
            Controller handler = requestMappingHandlerMapping.findHandler(req.getRequestURI()); // uri 에 맞는 handler 를 찾고
            String viewName = handler.handleRequest(req, res); // handleRequest 에게 작업을 위임함

            RequestDispatcher requestDispatcher = req.getRequestDispatcher(viewName);
            requestDispatcher.forward(req, res);

        } catch (Exception e) {
            log.error("exception occurred : [{}]", e.getMessage(), e);
            throw new ServletException(e);
        }
    }

}

