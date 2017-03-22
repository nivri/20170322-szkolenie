package com.example.spring.printers;

import com.example.spring.HelloWorld;

import javax.inject.Named;

@Named
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
