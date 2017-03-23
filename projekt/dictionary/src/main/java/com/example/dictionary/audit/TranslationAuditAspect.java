package com.example.dictionary.audit;

import com.example.dictionary.TranslationEvent;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Aspect
public class TranslationAuditAspect {

    final private ApplicationEventPublisher publisher;

    public TranslationAuditAspect(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @AfterReturning(value = "execution(* com.example.dictionary.translation.TranslationService.*(*))",
        returning = "translations")
    public void logTranslationResult(List<?> translations) {
        publisher.publishEvent(new TranslationEvent("translation.size() = " + translations.size()));
    }

}
