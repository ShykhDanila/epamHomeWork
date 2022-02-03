package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Validatable;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE extends DefaultBean {

    public BeanE(String name, double value) {
        super(name, value);
    }

    @PostConstruct
    public void postConstructBeanE() {
        System.out.println("postConstruct BeanE");
    }

    @PreDestroy
    public void preDestroyBeanE() {
        System.out.println("preDestroy BeanE");
    }

}
