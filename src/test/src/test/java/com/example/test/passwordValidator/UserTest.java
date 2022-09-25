package com.example.test.passwordValidator;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class UserTest {

    @DisplayName("패스워드가 요구사항에 부합해 초기화가 진행된다")
    @Test
    void passwordTest() {
        // Given
        User user = new User();
        // When
        user.initPassword(new CorrectFixedPasswordGenerator());
//        user.initPassword(()->"12345678");
//        메소드가 하나인 Functional Interface 를 구현한 메소드이기 때문에 람다로 선언도 가능함

        // Then
        assertThat(user.getPassword()).isNotNull();
    }

    @DisplayName("패스워드가 요구사항에 부합하지 않아 초기화가 되지 않는다")
    @Test
    void passwordTest2() {
        // Given
        User user = new User();
        // When
        user.initPassword(new WrongFixedPasswordGenerator());
        //        user.initPassword(()->"12");
        // Then
        assertThat(user.getPassword()).isNull();
    }
}