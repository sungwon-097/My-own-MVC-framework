package com.example.test;

import com.example.test.calculate.*;

import java.util.List;

public class Calculator {

//        1. 인자를 직접 주입해줌
//  public static int calculate(int operand1, String operator, int operand2) {
//        if("+".equals((operator)))
//            return operand1 + operand2;
//        else if ("-".equals((operator)))
//            return operand1 - operand2;
//
//        else if("*".equals((operator)))
//            return operand1 * operand2;
//
//        else if("/".equals((operator)))
//            return operand1 / operand2;
//        return 0;

//        2. Enum 으로 구현된 ArithmeticOperator 에게 위임함
//  public static int calculate(int operand1, String operator, int operand2) {
//        return ArithmeticOperator.calculate(operand1, operator, operand2);

//        3.calculate/ 패키지에 구현한 Operators 에서 작업을 하기 위해 NewArithmeticOperator 로 위임함
    private static final List<NewArithmeticOperator> arithmeticOperators = List.of(
            new AdditionOperator(), new SubtractionOperator(), new MultiplicationOperator(), new DivisionOperator()
);

    public static int calculate(PositiveNumber operand1, String operator, PositiveNumber operand2) {
        return arithmeticOperators.stream()
                .filter(arithmeticOperator -> arithmeticOperator.supports(operator))
                .map(arithmeticOperator -> arithmeticOperator.calculate(operand1, operand2))
                .findFirst()
                .orElseThrow(()-> new IllegalArgumentException("올바른 연산이 아닙니다"));
    }
}
