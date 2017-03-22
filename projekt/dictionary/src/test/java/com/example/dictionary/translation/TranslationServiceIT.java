package com.example.dictionary.translation;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TranslationService.class)
public class TranslationServiceIT {

    @Autowired
    TranslationService service;

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