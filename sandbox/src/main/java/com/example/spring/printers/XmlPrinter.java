package com.example.spring.printers;

import com.example.spring.HelloWorld;
import org.springframework.stereotype.Component;

@Component
@XmlFormat
class XmlPrinter implements Printer {

    final private HelloWorld parent;

    XmlPrinter(HelloWorld parent) {
        this.parent = parent;
    }

    @Override
    public String sayHello() {
        return "<xml>" + parent.sayHello() +  "</xml>";
    }

}
