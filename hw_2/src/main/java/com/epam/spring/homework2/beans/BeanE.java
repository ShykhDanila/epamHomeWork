package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class BeanE implements MyValidator {

    private String name;
    private double value;

    public BeanE(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanE{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @PostConstruct
    public void postConstructBeanE() {
        System.out.println("postConstruct BeanE");
    }

    @PreDestroy
    public void preDestroyBeanE() {
        System.out.println("preDestroy BeanE");
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanE";
        } else if (value < 0) {
            value = 0;
        }
    }
}
