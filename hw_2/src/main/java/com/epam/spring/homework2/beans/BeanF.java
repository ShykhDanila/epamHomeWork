package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;

public class BeanF implements MyValidator {

    private String name;
    private double value;

    public BeanF(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanF{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanF";
        } else if (value < 0) {
            value = 0;
        }
    }
}
