package com.example.test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatCode;

//-   Requirement
//        -   range(min 8 ~ max 12)
//        -   out of range => IllegalArgumentException
//        -   경계조건에 대해 테스트 코드를 작성 해야함

public class PasswordValidatorTest {
    @DisplayName("비밀번호가 최소 8자 이상, 12자 이하면 예외가 발생하지 않는다")
    @Test
    void validatePasswordTest() {
        assertThatCode(() -> PasswordValidator.validate("serverwizard"))
                .doesNotThrowAnyException();
    }

    @DisplayName("비밀번호가 8자 미만, 12자 초과 일 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"7자리패스워드", "len13password"})
    void validatePasswordTest2(String password){
        assertThatCode(()->PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8~12자 사이여야 한다.");
    }
}
