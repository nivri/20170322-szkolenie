package com.example.spring;

import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class Sandbox {

    public static void main(String[] args) {

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Cfg.class);

//        Map<String, Object> ofType = ctx.getBeansOfType(Object.class);
//        ofType.forEach((s,o) -> System.out.println(s + " :: " + o));

        Controller bean = ctx.getBean(Controller.class);
        bean.doRun();

        ctx.stop();
    }


    @Configuration
    @ComponentScan
    @EnableAspectJAutoProxy
    public static class Cfg {

        @Bean
        LocalValidatorFactoryBean validation() {
            return new LocalValidatorFactoryBean();
        }

        @Bean
        public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource messageSource =
                    new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }

    }
}

