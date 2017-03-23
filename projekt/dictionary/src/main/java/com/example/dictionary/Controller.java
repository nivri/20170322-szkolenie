package com.example.dictionary;

import com.example.dictionary.translation.DictionaryWord;
import com.example.dictionary.translation.TranslationService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

@Component
public class Controller {

    @Autowired
    Validator validator;

    @Autowired
	BeanFactory bf;

    final private TranslationService service;

	public Controller(TranslationService service) {
		this.service = service;
	}

	public void run() {
		boolean ok = true;
		Scanner s = new Scanner(System.in);
		while (ok) {
			System.out.print("dictionary > ");
			Params p = bf.getBean(Params.class, s.nextLine());

			if ("search".equals(p.getCommand())) {
                Set<ConstraintViolation<Params>> errors = validator.validate(p, p.validationGroup());
                if (!errors.isEmpty()) {
                    System.out.println("validate = " + errors);
                    continue;
                }

                List<DictionaryWord> wordList = service.getTranslationsForWord(p.getAttrs().get(0));
                wordList.forEach(System.out::println);
            }

			if ("exit".equals(p.getCommand())) {
				ok = false;
			}
		}
		s.close();
	}
	
}

