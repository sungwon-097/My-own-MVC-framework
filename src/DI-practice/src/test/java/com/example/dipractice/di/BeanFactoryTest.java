package com.example.dipractice.di;
import com.example.dipractice.annotation.Controller;
import com.example.dipractice.annotation.Service;
import com.example.dipractice.controller.UserController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class BeanFactoryTest {

    private Reflections reflections;
    private BeanFactory beanFactory;
    private static final Logger logger = LoggerFactory.getLogger(BeanFactoryTest.class);

    @BeforeEach // test 호출 전에 미리 실행하여 reflections 와 beanFactory 를 초기화해줌
    void setUp() {
        reflections = new Reflections("com.example");
        // UserController, User Service
        Set<Class <?>> preInstantiatedClazz = getTypesAnnotationWith(Controller.class, Service.class);
        logger.info("preInstantiatedClazz log [{}]", preInstantiatedClazz);
        beanFactory = new BeanFactory(preInstantiatedClazz);
    }

    @SafeVarargs
    private Set<Class<?>> getTypesAnnotationWith(Class<? extends Annotation>... annotations) {// 여러 타입의 객체가 여러개 들어 올 수 있음
        Set<Class<?>> beans = new HashSet<>();
        for(Class<? extends  Annotation> annotation: annotations){
            beans.addAll(reflections.getTypesAnnotatedWith(annotation));
        }
        return beans;
    }

    @Test
    void diTest() {
        UserController userController = beanFactory.getBean(UserController.class);

        assertThat(userController).isNotNull();
        logger.info("userController log [{}]", userController);
        assertThat(userController.getUserService()).isNotNull();
        logger.info("getUserService log [{}]", userController.getUserService());
    }
}