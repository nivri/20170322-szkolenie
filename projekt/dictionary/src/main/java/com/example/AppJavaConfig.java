package com.example;

import com.example.dictionary.audit.AuditConfiguration;
import com.example.dictionary.translation.TranslationConfiguration;
import com.example.dictionary.translation.TranslationService;
import org.springframework.context.annotation.*;
import org.springframework.context.support.AbstractApplicationContext;

import com.example.dictionary.Controller;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

public class AppJavaConfig {

	public static void main(String... args) {

		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(
				AppConfiguration.class);
		Controller c = ctx.getBean(Controller.class);
		c.run();

		ctx.close();
	}

	@Configuration
	@EnableAsync
	@Import({TranslationConfiguration.class, AuditConfiguration.class})
	public static class AppConfiguration {

		@Bean
		public Controller controller(TranslationService service) {
			return new Controller(service);
		}

	}

}
