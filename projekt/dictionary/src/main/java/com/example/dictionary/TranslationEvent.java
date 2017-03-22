package com.example.dictionary;

import org.springframework.context.ApplicationEvent;

public class TranslationEvent extends ApplicationEvent {

    private String payload;

    public TranslationEvent(String source) {
        super(source);
        this.payload = source;
    }


    public String getPayload() {
        return payload;
    }
}
