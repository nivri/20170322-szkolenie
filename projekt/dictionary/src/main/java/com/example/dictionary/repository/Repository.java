package com.example.dictionary.repository;

import com.example.dictionary.translation.DictionaryWord;

import java.util.List;

public interface Repository {

    List<DictionaryWord> getSavedWords();
    void addWord(DictionaryWord word);
    void printSavedWords();

}