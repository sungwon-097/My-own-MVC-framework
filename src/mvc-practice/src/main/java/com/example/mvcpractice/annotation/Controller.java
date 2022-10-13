package com.example.mvcpractice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE}) // 대상은 Type
@Retention(RetentionPolicy.RUNTIME) // Runtime 동안 동작
public @interface Controller{

}

