package com.example.www.controllers;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class WordsController {

    final private TranslationService service;

    public WordsController(TranslationService service) {
        this.service = service;
    }

    @RequestMapping(path = "/search", method = RequestMethod.GET)
    public void searchForWord(@RequestParam("word") String word) {
        List<DictionaryWord> wordList = service.getTranslationsForWord(word);
        System.out.println("wordList = " + wordList);
    }

}
