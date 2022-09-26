package com.example.test.passwordValidator;

import com.example.test.passwordValidator.PasswordGenerator;

public class WrongFixedPasswordGenerator implements PasswordGenerator {
    @Override
    public String generatePassword() {
        return "12";
    }
}
