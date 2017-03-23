package com.example.dictionary;

import com.example.dictionary.validation.SearchValidationGroup;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Arrays;
import java.util.List;

public class GenericParams implements Params {

    @NotNull
    @Size(min = 4)
    String command;

    @NotNull(groups = SearchValidationGroup.class)
    @Size(min = 1, max = 1, groups = SearchValidationGroup.class)
    List<String> attrs;

    public static Params ofString(String line) {
        List<String> split = Arrays.asList(line.split(" "));
        if (split.size() == 1) {
            return new GenericParams(split.get(0));
        } else {
            return new GenericParams(split.get(0), split.subList(1, split.size()));
        }
    }

    private GenericParams(String command) {
        this.command = command;
    }

    private GenericParams(String command, List<String> attrs) {
        this.command = command;
        this.attrs = attrs;
    }

    @Override
    public String getCommand() {
        return command;
    }

    @Override
    public List<String> getAttrs() {
        return attrs;
    }

    @Override
    public String toString() {
        return "GenericParams{" +
                "command='" + command + '\'' +
                ", attrs=" + attrs +
                '}';
    }

    @Override
    public Class<?> validationGroup() {
        if ("search".equals(getCommand())) {
            return SearchValidationGroup.class;
        }

        return javax.validation.groups.Default.class;
    }
}
