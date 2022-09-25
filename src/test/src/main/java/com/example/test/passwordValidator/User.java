package com.example.test.passwordValidator;

import com.example.test.passwordValidator.PasswordGenerator;

public class User {

    private String password;

    public void initPassword(PasswordGenerator passwordGenerator){
//        as-is, 내부에서 객체를 생성 하는 것은 강한 결합, 영향이 큼
//        RandomPasswordGenerator randomPasswordGenerator = new RandomPasswordGenerator();

//        to-be, 상위에 인터페이스를 선언함으로서 낮은 결합도를 가짐
//        Test 패키지에 Correct*, Wrong* 클래스를 생성해 generatePassword() 메소드의 재정의가 가능해진다
        String password = passwordGenerator.generatePassword();

        if(password.length() >= 8 && password.length() <= 12)
            this.password = password;
    }

    public String getPassword() {
        return this.password;
    }
}
