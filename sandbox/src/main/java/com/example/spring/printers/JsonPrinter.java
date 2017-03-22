package com.example.spring.printers;

import com.example.spring.HelloWorld;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@JsonFormat
public class JsonPrinter implements Printer {

    private final HelloWorld hello;

    public JsonPrinter(HelloWorld hello) {
        this.hello = hello;
    }

    @Override
    public String sayHello() {
        return "{\"hello\": \"" + hello.sayHello() + "\"}";
    }
}
