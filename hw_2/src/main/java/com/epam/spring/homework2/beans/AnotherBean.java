package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class AnotherBean implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyValidator) {
            ((MyValidator) bean).validate();
        }
        return bean;
    }
}
