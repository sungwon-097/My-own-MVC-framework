package com.example.test;

import org.junit.jupiter.api.Test;

public class CorrectFixedPasswordGenerator implements PasswordGenerator{
    @Override
    public String generatePassword() {
        return "12345678";
    }
}
