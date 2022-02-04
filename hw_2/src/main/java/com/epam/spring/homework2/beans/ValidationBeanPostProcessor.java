package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Validatable;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

@Component
public class ValidationBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof Validatable) {
            ((Validatable) bean).validate();
        }
        return bean;
    }
}
