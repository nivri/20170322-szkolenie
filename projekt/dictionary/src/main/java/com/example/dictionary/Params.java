package com.example.dictionary;

import java.util.List;

public interface Params {
    String getCommand();

    List<String> getAttrs();

    Class<?> validationGroup();
}
