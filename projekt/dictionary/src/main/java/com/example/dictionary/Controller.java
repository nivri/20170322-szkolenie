package com.example.dictionary;

import java.util.List;
import java.util.Scanner;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.stereotype.Component;

@Component
public class Controller {

	private final TranslationService service;

	public Controller(TranslationService service) {
		this.service = service;
	}

	public void run() {
		boolean ok = true;
		Scanner s = new Scanner(System.in);
		while (ok) {
			System.out.print("dictionary > ");
			String command = s.nextLine();

			if (command.startsWith("search")) {
				String word = command.split(" ")[1];
                List<DictionaryWord> wordList = service.getTranslationsForWord(word);
                wordList.forEach(System.out::println);
            }

			if ("exit".equals(command)) {
				ok = false;
			}
		}
		s.close();
	}
	
}
