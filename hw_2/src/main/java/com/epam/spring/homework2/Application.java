package com.epam.spring.homework2;

import com.epam.spring.homework2.config.BeansConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeansConfig.class);

        System.out.println("===============");
        for (String str :
                context.getBeanDefinitionNames()) {
            System.out.println(context.getBean(str));
        }
        System.out.println("===============");

        context.close();
    }
}
