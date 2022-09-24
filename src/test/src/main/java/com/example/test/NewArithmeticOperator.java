package com.example.test;

import com.example.test.calculate.PositiveNumber;

import java.util.Arrays;

public interface NewArithmeticOperator {
    boolean supports(String operator);
    int calculate(PositiveNumber operand1, PositiveNumber operand2);
}
