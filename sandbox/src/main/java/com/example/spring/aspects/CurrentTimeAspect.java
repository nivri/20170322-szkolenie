package com.example.spring.aspects;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
public class CurrentTimeAspect {

    @Before("execution(* com.example.spring.printers.*.*())")
    public void logCurrentInvocation(JoinPoint p) {
        System.out.println(p.getTarget().getClass().getSimpleName() + " :: " +  System.currentTimeMillis());
    }

    @Around("bean(*)")
    public Object log(ProceedingJoinPoint p) throws Throwable {
        long startTime = System.currentTimeMillis();

        Object o = p.proceed();

        System.out.println(p.getSignature() + ", totalTime: " + (System.currentTimeMillis() - startTime) + "ms");

        return o;
    }


}
