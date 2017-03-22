package com.example.spring.validation;

import com.example.spring.validation.SearchValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Params {

    @NotNull
    @Size(min = 4)
    String command;

    @NotNull(groups = SearchValidationGroup.class)
    @Size.List({
        @Size(min = 1, max = 1, groups = SearchValidationGroup.class)
    })
    String[] attr;

    public Params(String command, String[] attr) {
        this.command = command;
        this.attr = attr;
    }

    public Params(String command) {
        this.command = command;
    }

    public String getCommand() {
        return command;
    }

    public String[] getAttr() {
        return attr;
    }
}
