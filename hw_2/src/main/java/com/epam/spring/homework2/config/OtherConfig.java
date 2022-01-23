package com.epam.spring.homework2.config;

import com.epam.spring.homework2.beans.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.ComponentScan;

@Configuration
@ComponentScan("com.epam.spring.homework2.beans")
@PropertySource("my.properties")
public class OtherConfig {

    @Value("${beanB.name}")
    private String nameBeanB;
    @Value("${beanB.value}")
    private double valueBeanB;
    @Value("${beanC.name}")
    private String nameBeanC;
    @Value("${beanC.value}")
    private double valueBeanC;
    @Value("${beanD.name}")
    private String nameBeanD;
    @Value("${beanD.value}")
    private double valueBeanD;

    @Bean
    @DependsOn(value = {"beanD", "beanB", "beanC"})
    public BeanA beanA() {
        System.out.println("beanA");
        return new BeanA("Oleg", -12.43);
    }

    @Bean(initMethod = "initBeanB", destroyMethod = "destroyBeanB")
    public BeanB beanB() {
        System.out.println("beanB");
        return new BeanB(nameBeanB, valueBeanB);
    }

    @Bean(initMethod = "initBeanC", destroyMethod = "destroyBeanC")
    public BeanC beanC() {
        System.out.println("beanC");
        return new BeanC(nameBeanC, valueBeanC);
    }

    @Bean(initMethod = "initBeanD", destroyMethod = "destroyBeanD")
    public BeanD beanD() {
        System.out.println("beanD");
        return new BeanD(nameBeanD, valueBeanD);
    }

    @Bean
    public BeanE beanE() {
        System.out.println("beanE");
        return new BeanE(null, 32.21);
    }

    @Bean
    @Lazy
    public BeanF beanF() {
        System.out.println("beanF");
        return new BeanF("BeanF", -21.32);
    }
}
