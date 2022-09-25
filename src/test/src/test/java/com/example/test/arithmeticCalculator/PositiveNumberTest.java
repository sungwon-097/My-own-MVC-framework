package com.example.test.arithmeticCalculator;

import com.example.test.arithmeticCalculator.calculate.PositiveNumber;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

public class PositiveNumberTest {
    @ParameterizedTest
    @ValueSource(ints = {0, -1})
    void createTest(int value) {
        assertThatCode(()-> new PositiveNumber(value))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("0또는 음수를 전달 할 수 없습니다");
    }
}
