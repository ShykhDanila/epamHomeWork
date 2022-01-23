package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;

public class BeanB implements MyValidator {

    private String name;
    private double value;

    public BeanB(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanB{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initBeanB() {
        System.out.println("init BeanB");
    }

    private void myNewCustomInitMethod() {
        System.out.println("my new init BeanB");
    }

    private void destroyBeanB() {
        System.out.println("destroy BeanB");
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanB";
        } else if (value < 0) {
            value = 0;
        }
    }
}
