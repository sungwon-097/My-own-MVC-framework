package com.example.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class CalculatorTest {
    
    @DisplayName("덧셈 연산을 수행한다")
    @Test
    void additionTest(){
        int result = Calculator.calculate(1,"+",2);
        assertThat(result).isEqualTo(3);
    }

    @DisplayName("뺄셈 연산을 수행한다")
    @Test
    void subtractionTest(){
        int result = Calculator.calculate(1,"-",2);
        assertThat(result).isEqualTo(-1);
    }

    @DisplayName("연산을 수행한다")
    @ParameterizedTest
    @MethodSource("formulaAndResult")
    void calculateTest(int operand1, String operator, int operand2, int result){
        int calculateResult = Calculator.calculate(operand1,operator,operand2);
        assertThat(calculateResult).isEqualTo(result);
    }

    public static Stream<Arguments> formulaAndResult(){
        return Stream.of(
                arguments(1,"+",2,3),
                arguments(1,"-",2,-1),
                arguments(1,"*",2,2),
                arguments(1,"/",2,0)
        );
    }
}
