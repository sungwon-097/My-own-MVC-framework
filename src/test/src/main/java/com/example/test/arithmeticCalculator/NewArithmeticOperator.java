package com.example.test.arithmeticCalculator;

import com.example.test.arithmeticCalculator.calculate.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
