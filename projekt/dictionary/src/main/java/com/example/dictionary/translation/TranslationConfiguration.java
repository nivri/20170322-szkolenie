package com.example.dictionary.translation;

import com.example.dictionary.Controller;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan
@PropertySource("classpath:dict.properties")
public class TranslationConfiguration {

}
