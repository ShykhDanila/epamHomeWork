package com.epam.spring.homework2.beans;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class BeanA extends DefaultBean implements InitializingBean, DisposableBean {

    public BeanA(String name, double value) {
        super(name, value);
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("destroy BeanA");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet BeanA");
    }
}
