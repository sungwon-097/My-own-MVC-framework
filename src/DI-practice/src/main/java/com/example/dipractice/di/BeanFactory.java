package com.example.dipractice.di;

import com.example.dipractice.annotation.Inject;
import org.reflections.ReflectionUtils;
import org.reflections.Reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz=preInstantiatedClazz;
        initialize();
    }

    private void initialize(){
        for (Class<?> clazz : preInstantiatedClazz){
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }
    // userController 에 대한 클래스 타입 객체가 들어옴
    // 생성자와 파라미터 정보를 가지고 와서 getParameterByClass 를 호출함
    // userService 에 대한 인스턴스가 아직 만들어지지 않았기 때문에 createInstance 를 다시 호출함
    // userService 는 parameter 가 없기 때문에 인스턴스 생성 단계로 넘어감

    // userController 의 인스턴스를 생성하려면 userService 의 인스턴스가 필요하기 때문에 재귀적으로 createInstance 를 호출한다
    private Object createInstance(Class<?> clazz){
        // 생성자를 찾음
        Constructor<?> constructor = findConstructor(clazz);
        // 각 파라미터 타입에 대한 인스턴스를 가지고 옴
        List<Object> parameters = new ArrayList<>();
        for(Class<?> typeClass : constructor.getParameterTypes()){
            // UserService
            parameters.add(getParameterByClass(typeClass));
        }
        // 인스턴스 생성
        try{
            return constructor.newInstance(parameters.toArray());
        }catch (InstantiationException | IllegalAccessException | InvocationTargetException e){
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz){
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);
        if (Objects.nonNull(constructor)){
            return constructor;
        }
        return clazz.getConstructors()[0];
    }

    private Object getParameterByClass(Class<?> typeClass) {
        Object instanceBeans = getBean(typeClass);
        if (Objects.nonNull(instanceBeans)){
            return instanceBeans;
        }
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}