package com.example.www.controllers;

import com.example.dictionary.repository.Repository;
import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class WordsController {

    final private TranslationService service;
    final private Repository repository;

    public WordsController(TranslationService service, Repository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping(path = "/search/{word}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<DictionaryWord> searchForWord(@PathVariable("word") String word) {
        return service.getTranslationsForWord(word);
    }

    @PostMapping("/search/{word}")
    public ResponseEntity save(@PathVariable("word") String word, @RequestParam("save") Integer i) {
        List<DictionaryWord> words = service.getTranslationsForWord(word);
        if (words.size() < i) {
            return ResponseEntity.badRequest().build();
        }

        repository.addWord(words.get(i));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public List<DictionaryWord> savedWords() {
        return repository.getSavedWords();
    }


}
