package com.example.test;

public class PasswordValidator {
    public static void validate(String password) {
        int len = password.length();
        if(len < 8||len > 12){
            throw new IllegalArgumentException("비밀번호는 8~12자 사이여야 한다.");
        }
    }
}
