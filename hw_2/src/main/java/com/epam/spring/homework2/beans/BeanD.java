package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;

public class BeanD implements MyValidator {

    private String name;
    private double value;

    public BeanD(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanD{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    private void initBeanD() {
        System.out.println("init BeanD");
    }

    private void destroyBeanD() {
        System.out.println("destroy BeanD");
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanD";
        } else if (value < 0) {
            value = 0;
        }
    }
}
