package com.example.spring.db;

public class DictionaryWord {
    final String polishWord;
    final String englishWord;

    public DictionaryWord(String polishWord, String englishWord) {
        this.polishWord = polishWord;
        this.englishWord = englishWord;
    }

    @Override
    public String toString() {
        return "DictionaryWord{" +
                "polishWord='" + polishWord + '\'' +
                ", englishWord='" + englishWord + '\'' +
                '}';
    }
}
