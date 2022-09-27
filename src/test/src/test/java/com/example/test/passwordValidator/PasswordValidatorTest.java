package com.example.test.passwordValidator;

import com.example.test.passwordValidator.PasswordValidator;
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
    @ParameterizedTest
    @ValueSource(strings = {"123123123"})
    void validatePasswordTest(String password) {
        assertThatCode(() -> PasswordValidator.validate(password))
                .doesNotThrowAnyException();
    }
    // TDD 방식으로 테스트 코드 먼저 설계 후 main 코드 작성

    @DisplayName("비밀번호가 8자 미만, 12자 초과 일 경우 IllegalArgumentException 예외가 발생한다.")
    @ParameterizedTest
    @ValueSource(strings = {"7자리패스워드", "len13password"})
    void validatePasswordTest2(String password){
        assertThatCode(()->PasswordValidator.validate(password))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("비밀번호는 8~12자 사이여야 한다.");
    }
    // 예외의 종류와 메시지 반환 확인
}