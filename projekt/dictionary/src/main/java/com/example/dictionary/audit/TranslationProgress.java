package com.example.dictionary.audit;

import com.example.dictionary.TranslationEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Async
public class TranslationProgress {

    private static Logger logger = LoggerFactory.getLogger(TranslationProgress.class);

    @EventListener
    public void logTranslation(TranslationEvent event) {
        logger.info(event.getPayload());
    }

}
