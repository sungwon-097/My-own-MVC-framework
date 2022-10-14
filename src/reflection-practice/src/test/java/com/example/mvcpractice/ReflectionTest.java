package com.example.mvcpractice;

import com.example.mvcpractice.annotation.Controller;
import com.example.mvcpractice.annotation.Service;
import com.example.mvcpractice.model.User;
import javassist.tools.reflect.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import java.lang.annotation.Annotation;
import java.util.*;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;

public class ReflectionTest {

    private static final Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    @DisplayName("@Controller 가 설정된 모든 클래스를 찾아 출력")
    void componentScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(List.of(Controller.class, Service.class));

        logger.info("beans: [{}]", beans);
    }

    @Test
    @DisplayName("클래스의 정보를 가져옴")
    void showClass() throws NoSuchMethodException {
        Class<User> clazz = User.class;
        logger.info(clazz.getName());

        logger.info("User all declared fields: [{}]"
                , Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        logger.info("User all declared constructors: [{}]"
                , Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        logger.info("User all declared methods: [{}]"
                , Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }

    @Test
    @DisplayName("세 가지 방법으로 HEAP에서 클래스를 가져와 서로 같은지 확인")
    void load() throws ClassNotFoundException {
        Class<User> clazz1 = User.class;

        User user = new User("id", "name");
        Class<? extends User> clazz2 = user.getClass();

        Class<?> clazz3 = Class.forName("com.example.mvcpractice.model.User");

        logger.info("clazz : [{}]", clazz1);
        assertThat(clazz1.equals(clazz2)).isTrue();
        assertThat(clazz1.equals(clazz3)).isTrue();
    }

    private Set<Class<?>> getTypesAnnotatedWith(List<Class<? extends Annotation>> annotations) {
        Reflections reflections = new Reflections("com.example");

        Set<Class<?>> beans = new HashSet<>();
        annotations.forEach(annotation -> beans.addAll(reflections.getTypesAnnotatedWith(annotation)));

        return beans;
    }
}