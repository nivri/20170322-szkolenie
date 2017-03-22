package com.example.spring;

import com.example.spring.printers.Printer;
import com.example.spring.validation.Params;
import com.example.spring.validation.SearchValidationGroup;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.Size;
import javax.validation.groups.Default;
import java.util.Set;

public class Sandbox {

    public static void main(String[] args) {

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Cfg.class);

//        Map<String, Object> ofType = ctx.getBeansOfType(Object.class);
//        ofType.forEach((s,o) -> System.out.println(s + " :: " + o));

        Validator validator = ctx.getBean(Validator.class);

        {
            Params p =  new Params("search");
            Set<ConstraintViolation<Params>> violations = validator.validate(p, SearchValidationGroup.class);
            System.out.println("violations = " + violations);
        }

        {
            Params p =  new Params("exit");
            Set<ConstraintViolation<Params>> violations = validator.validate(p);
            System.out.println("violations = " + violations);
        }


        ctx.stop();
    }


    @Configuration
    @ComponentScan
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

