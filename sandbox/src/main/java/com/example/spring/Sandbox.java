package com.example.spring;

import com.example.spring.db.DictionaryWord;
import com.example.spring.db.WordsQuery;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.util.List;

public class Sandbox {

    public static void main(String[] args) throws Exception {

        GenericApplicationContext ctx = new AnnotationConfigApplicationContext(Cfg.class);

//        Map<String, Object> ofType = ctx.getBeansOfType(Object.class);
//        ofType.forEach((s,o) -> System.out.println(s + " :: " + o));

        Controller bean = ctx.getBean(Controller.class);
        bean.doRun();

        WordsQuery wordsQuery = ctx.getBean(WordsQuery.class);
        List<DictionaryWord> execute = wordsQuery.execute();
        System.out.println("execute = " + execute);

//        DataSource dataSource = ctx.getBean(DataSource.class);
//
//        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
//
//        List<String> query = jdbcTemplate.query("SELECT * FROM WORDS", (rs, rowNum) ->
//                rs.getString("polish_word") + " :: " + rs.getString("english_word"));
//
//        System.out.println("query = " + query);

        ctx.stop();
    }


    @Configuration
    @ComponentScan
    @EnableAspectJAutoProxy
    public static class Cfg {

        @Bean
        LocalValidatorFactoryBean validation() {
            return new LocalValidatorFactoryBean();
        }

        @Bean
        public ResourceBundleMessageSource messageSource() {
            ResourceBundleMessageSource messageSource =
                    new ResourceBundleMessageSource();
            messageSource.setBasename("messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }

        @Bean
        public DataSource ds() {
//            EmbeddedDatabase ds = new EmbeddedDatabaseBuilder()
//                    .setType(EmbeddedDatabaseType.HSQL)
//                    .build();
//
//            return ds;
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(org.hsqldb.jdbc.JDBCDriver.class.getName());
            ds.setUrl("jdbc:hsqldb:file:/tmp/sandboxdb/");
            ds.setUsername("SA");
            ds.setPassword("");

            return ds;
        }

    }
}

