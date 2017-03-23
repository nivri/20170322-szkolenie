package com.example.dictionary;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ParamsAspect {

    @AfterReturning(value = "execution(* com.example.dictionary.Params.validationGroup())",
        returning = "group")
    public void logValidationGroup(Class<?> group) {
        System.out.println("validationGroup = " + group);
    }

}
