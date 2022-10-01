package com.example.waspractice.calculator.domain;

import com.example.waspractice.calculator.tobe.PositiveNumber;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
