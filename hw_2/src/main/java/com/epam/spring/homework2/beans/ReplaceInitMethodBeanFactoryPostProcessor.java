package com.epam.spring.homework2.beans;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class ReplaceInitMethodBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public ReplaceInitMethodBeanFactoryPostProcessor() {
        System.out.println("OtherBean");
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
        System.out.println("postProcessBeanFactory OtherBean");
        System.out.println("beanB init method name : " + configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
        configurableListableBeanFactory.getBeanDefinition("beanB").setInitMethodName("myNewCustomInitMethod");
        System.out.println("beanB new init method name : " + configurableListableBeanFactory.getBeanDefinition("beanB").getInitMethodName());
        System.out.println("postProcessBeanFactory OtherBean replace init method beanB");
    }
}
