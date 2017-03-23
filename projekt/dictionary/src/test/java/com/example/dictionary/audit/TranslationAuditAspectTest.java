package com.example.dictionary.audit;

import com.example.dictionary.TranslationEvent;
import org.junit.Test;
import org.springframework.context.ApplicationEventPublisher;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class TranslationAuditAspectTest {

    @Test
    public void should_publish_event() {
        //given
        ApplicationEventPublisher mock = mock(ApplicationEventPublisher.class);
        List<String> s = Arrays.asList("1", "2");
        TranslationAuditAspect aspect = new TranslationAuditAspect(mock);

        //when
        aspect.logTranslationResult(s);

        //then
        verify(mock).publishEvent(eq(new TranslationEvent("translation.size() = " + s.size())));
    }

}