package com.example.dictionary.audit;

import com.example.dictionary.TranslationEvent;
import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationConfiguration;
import com.example.dictionary.translation.TranslationService;
import com.example.dictionary.translation.TranslationServiceTest;
import org.hamcrest.CoreMatchers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {TranslationAuditAspectSpringTest.Cfg.class, TranslationAuditAspectSpringTest.TestListener.class,
        TranslationConfiguration.class, TranslationAuditAspect.class})
public class TranslationAuditAspectSpringTest {

    @Autowired
    TranslationService service;

    @Autowired
    TestListener listener;

    @Test
    public void should_publish_event() {
        List<DictionaryWord> list = service.getTranslationsForWord("book");

        assertThat(listener.payload, CoreMatchers.equalTo("translation.size() = 24"));
    }

    @Component
    public static class TestListener {

        String payload;

        @EventListener
        public void logTranslation(TranslationEvent event) {
            payload = event.getPayload();
        }

    }

    @Configuration
    @EnableAspectJAutoProxy
    public static class Cfg {

    }

}