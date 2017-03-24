package com.example.www.controllers;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class WordsController {

    final private TranslationService service;

    public WordsController(TranslationService service) {
        this.service = service;
    }

    @GetMapping(path = "/search", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<DictionaryWord> searchForWord(@RequestParam("word") String word) {
        return service.getTranslationsForWord(word);
    }

}
