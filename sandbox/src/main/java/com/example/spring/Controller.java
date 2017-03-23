package com.example.spring;

import com.example.spring.printers.JsonFormat;
import com.example.spring.printers.Printer;
import com.example.spring.printers.XmlFormat;
import org.springframework.stereotype.Component;

@Component
public class Controller {

    final Printer printer;

    public Controller(@XmlFormat Printer printer) {
        this.printer = printer;
    }

    public void doRun() {
        System.out.println("Printed: " + printer.sayHello());
    }

}
