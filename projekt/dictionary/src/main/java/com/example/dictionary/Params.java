package com.example.dictionary;

import com.example.dictionary.validation.SearchValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

public class Params {

    @NotNull
    @Size(min = 4)
    String command;

    @NotNull(groups = SearchValidationGroup.class)
    @Size(min = 1, max = 1, groups = SearchValidationGroup.class)
    List<String> attrs;

    public static Params ofString(String line) {
        List<String> split = Arrays.asList(line.split(" "));
        if (split.size() == 1) {
            return new Params(split.get(0));
        } else {
            return new Params(split.get(0), split.subList(1, split.size()));
        }
    }

    private Params(String command) {
        this.command = command;
    }

    private Params(String command, List<String> attrs) {
        this.command = command;
        this.attrs = attrs;
    }

    public String getCommand() {
        return command;
    }

    public List<String> getAttrs() {
        return attrs;
    }
}
