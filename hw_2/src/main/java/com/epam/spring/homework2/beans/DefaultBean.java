package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.Validatable;

public class DefaultBean implements Validatable {

    protected String name;
    protected double value;

    public DefaultBean(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return
                getClass().getSimpleName() +
                        " {name='" + name + '\'' +
                        ", value=" + value +
                        '}';
    }


    @Override
    public void validate() {
        if (name == null || value < 0) {
            System.out.println("bean " + name + " is invalid");
        }
    }
}
