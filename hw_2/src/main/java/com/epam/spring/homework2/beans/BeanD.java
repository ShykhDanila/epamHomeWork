package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Validatable;

public class BeanD extends DefaultBean {

    public BeanD(String name, double value) {
        super(name, value);
    }

    private void initBeanD() {
        System.out.println("init BeanD");
    }

    private void destroyBeanD() {
        System.out.println("destroy BeanD");
    }
}
