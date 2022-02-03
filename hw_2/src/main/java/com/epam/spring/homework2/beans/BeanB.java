package com.epam.spring.homework2.beans;

public class BeanB extends DefaultBean {

    public BeanB(String name, double value) {
        super(name, value);
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
}
