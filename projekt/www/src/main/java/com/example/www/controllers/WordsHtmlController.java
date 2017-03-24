package com.example.www.controllers;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
public class WordsHtmlController {

    final private TranslationService service;

    public WordsHtmlController(TranslationService service) {
        this.service = service;
    }

    @GetMapping(value = "/search/{word}.html", produces = {
            MediaType.TEXT_HTML_VALUE, MediaType.APPLICATION_JSON_VALUE
    })
    public ModelAndView search(@PathVariable("word") String word) {
        List<DictionaryWord> words = service.getTranslationsForWord(word);

        ModelAndView mw = new ModelAndView("search");
        mw.addObject("word", word);
        mw.addObject("translations", words);

        return mw;
    }


}
