package com.example.dictionary;

import com.example.dictionary.repository.Repository;
import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.*;

@Component
public class Controller {

    @Autowired
    Validator validator;

    @Autowired
	BeanFactory bf;

    final private TranslationService service;
    final private Repository repository;

	public Controller(TranslationService service, Repository repository) {
		this.service = service;
		this.repository = repository;
	}

	public void run() {
		boolean ok = true;
		Scanner s = new Scanner(System.in);

		final List<DictionaryWord> wordList = new ArrayList();
		while (ok) {
			System.out.print("dictionary > ");
			Params p = bf.getBean(Params.class, s.nextLine());

			if ("search".equals(p.getCommand())) {
                Set<ConstraintViolation<Params>> errors = validator.validate(p, p.validationGroup());
                if (!errors.isEmpty()) {
                    System.out.println("validate = " + errors);
                    continue;
                }

                wordList.clear();
                wordList.addAll(service.getTranslationsForWord(p.getAttrs().get(0)));
                wordList.forEach(d -> System.out.println(wordList.indexOf(d) + ":: " + d));
            }

            if ("save".equals(p.getCommand())) {
				Integer index = Integer.valueOf(p.getAttrs().get(0));
				repository.addWord(wordList.get(index));
			}

			if ("print".equals(p.getCommand())) {
				repository.printSavedWords();
			}

			if ("exit".equals(p.getCommand())) {
				ok = false;
			}
		}
		s.close();
	}
	
}

