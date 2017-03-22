package com.example;

import com.example.dictionary.translation.TranslationConfiguration;
import com.example.dictionary.translation.TranslationService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;

import com.example.dictionary.Controller;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

public class AppJavaConfig {

	public static void main(String... args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				AppConfiguration.class);
		Controller c = ctx.getBean(Controller.class);
		c.run();

		ctx.close();
	}

	@Configuration
	@Import(TranslationConfiguration.class)
	public static class AppConfiguration {

		@Bean
		public Controller controller(TranslationService service) {
			return new Controller(service);
		}

	}

}
