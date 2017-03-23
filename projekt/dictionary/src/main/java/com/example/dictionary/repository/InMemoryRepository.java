package com.example.dictionary.repository;

import com.example.dictionary.translation.DictionaryWord;
import com.sun.javafx.UnmodifiableArrayList;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class InMemoryRepository implements Repository {

    private final Set<DictionaryWord> store = new HashSet<DictionaryWord>();

    @Override
    public List<DictionaryWord> getSavedWords() {
        return new UnmodifiableArrayList(store.toArray(new DictionaryWord[] {}), store.size());
    }

    @Override
    public void addWord(DictionaryWord word) {
        store.add(word);
    }

    @Override
    public void printSavedWords() {
        store.forEach(System.out::println);
    }
}
