package com.example.mvcpractice.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD}) // 클래스와 메소드에 모두 사용 할 수 있음
@Retention(RetentionPolicy.RUNTIME)
public @interface RequestMapping {
    String value()default "";

    RequestMethod[] method() default {};
}
