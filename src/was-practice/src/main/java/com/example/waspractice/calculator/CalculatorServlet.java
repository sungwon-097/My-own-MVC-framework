package com.example.waspractice.calculator;

import com.example.waspractice.calculator.domain.Calculator;
import com.example.waspractice.calculator.tobe.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate") // Mapping URI path and current Servlet
public class CalculatorServlet extends HttpServlet {
    // GenericServlet 은 추상 클래스이기 때문에 Servlet 인터페이스 구현과 달리 필요한 메소드만 구현하면 됨

    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    private ServletConfig servletConfig;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1),operator ,new PositiveNumber(operand2));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

//    @Override
//    public void init(ServletConfig servletConfig) throws ServletException {
//        log.info("init");
//    }

//    @Override
//    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
//        log.info("service");
//        int operand1 = Integer.parseInt(request.getParameter("operand1"));
//        String operator = request.getParameter("operator");
//        int operand2 = Integer.parseInt(request.getParameter("operand2"));
//
//        int result = Calculator.calculate(new PositiveNumber(operand1),operator ,new PositiveNumber(operand2));
//
//        PrintWriter writer = response.getWriter();
//        writer.println(result);
//    }
}
