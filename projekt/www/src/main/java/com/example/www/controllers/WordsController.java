package com.example.www.controllers;

import com.example.dictionary.repository.Repository;
import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.DeferredResult;

import java.security.Principal;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.CompletableFuture.supplyAsync;

@RestController
public class WordsController {

    final private TranslationService service;
    final private Repository repository;

    public WordsController(TranslationService service, Repository repository) {
        this.service = service;
        this.repository = repository;
    }

    @GetMapping(path = "/search/{word}", headers = "X-Version=1",
            produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public List<DictionaryWord> searchForWord(@PathVariable("word") String word) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        authentication.getAuthorities().forEach(System.out::println);
        return service.getTranslationsForWord(word);
    }

    @PostMapping(value = "/search/{word}")
    public ResponseEntity save(@PathVariable("word") String word, @RequestParam("save") Integer i) {
        List<DictionaryWord> words = service.getTranslationsForWord(word);
        if (words.size() < i) {
            return ResponseEntity.badRequest().build();
        }

        repository.addWord(words.get(i));

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public CompletableFuture<List<DictionaryWord>> savedWords() {
//        DeferredResult<List<DictionaryWord>> result = new DeferredResult<>();

        System.out.println("Pre-System.currentTimeMillis() = " + System.currentTimeMillis());

        return supplyAsync(() -> {
                    try {
                        Thread.sleep(5000);
                    } catch (Exception e) {}
                    return repository.getSavedWords();
                });
    }

//    @GetMapping("/")
    public List<DictionaryWord> savedWordsSync() {

        System.out.println("Pre-System.currentTimeMillis() = " + System.currentTimeMillis());

        CompletableFuture<List<DictionaryWord>> future =
                supplyAsync(() -> repository.getSavedWords());

        System.out.println("Post-System.currentTimeMillis() = " + System.currentTimeMillis());

        return future.join();
    }


}
