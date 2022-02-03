package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Validatable;

public class BeanC extends DefaultBean {

    public BeanC(String name, double value) {
        super(name, value);
    }

    private void initBeanC() {
        System.out.println("init BeanC");
    }

    private void destroyBeanC() {
        System.out.println("destroy BeanC");
    }
}
