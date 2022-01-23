package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;

public class BeanC implements MyValidator {

    private String name;
    private double value;

    public BeanC(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanC{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initBeanC() {
        System.out.println("init BeanC");
    }

    private void destroyBeanC() {
        System.out.println("destroy BeanC");
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanC";
        } else if (value < 0) {
            value = 0;
        }
    }
}
