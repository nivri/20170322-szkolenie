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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TranslationEvent that = (TranslationEvent) o;

        return payload != null ? payload.equals(that.payload) : that.payload == null;
    }

    @Override
    public int hashCode() {
        return payload != null ? payload.hashCode() : 0;
    }
}
