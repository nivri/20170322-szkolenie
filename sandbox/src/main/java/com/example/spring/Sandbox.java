package com.example.spring;

import com.example.spring.printers.Printer;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;

public class Sandbox {

    public static void main(String[] args) {

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Cfg.class);

//        Map<String, Object> ofType = ctx.getBeansOfType(Object.class);
//        ofType.forEach((s,o) -> System.out.println(s + " :: " + o));

        ctx.getBean(Controller.class).run();

        ctx.stop();
    }

    @Configuration
    @ComponentScan
    public static class Cfg {
    }
}

