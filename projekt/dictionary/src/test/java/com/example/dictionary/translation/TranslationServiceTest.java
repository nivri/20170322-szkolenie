package com.example.dictionary.translation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

public class TranslationServiceTest {

    final String ROOT_FOLDER = TranslationServiceTest.class.getResource("/words/").toExternalForm();

    TranslationService service = new TranslationService(ROOT_FOLDER + "/{}.html");

    @Test
    public void should_return_translations_for_word_book() {
        List<DictionaryWord> books = service.getTranslationsForWord("book");

        assertThat(books, hasSize(24));
        assertThat(books.get(0), equalTo(new DictionaryWord("książka", "book")));
    }

    @Test
    public void should_return_empty_list_for_non_existing_translation() {
        List<DictionaryWord> books = service.getTranslationsForWord("yadayada");

        assertThat(books, hasSize(0));
    }
}