package com.epam.spring.homework2.beans;

import com.epam.spring.homework2.validator.MyValidator;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA implements InitializingBean, DisposableBean, MyValidator {

    private String name;
    private double value;

    public BeanA(String name, double value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public String toString() {
        return "BeanA{" +
                "name='" + name + '\'' +
                ", value=" + value +
                '}';
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy BeanA");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet BeanA");
    }

    @Override
    public void validate() {
        if (name == null || name.isEmpty()) {
            name = "VALIDATE beanA";
        } else if (value < 0) {
            value = 0;
        }
    }
}
