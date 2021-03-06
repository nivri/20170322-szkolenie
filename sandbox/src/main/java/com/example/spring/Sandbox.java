package com.example.spring;

import com.example.spring.db.DictionaryWord;
import com.example.spring.db.WordsQuery;
import com.example.spring.db.dbi.WordsDao;
import org.skife.jdbi.v2.DBI;
import org.springframework.context.annotation.*;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.sql.DataSource;
import java.util.List;

public class Sandbox {

    public static void main(String[] args) throws Exception {

        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(Cfg.class);
//        ctx.getEnvironment().setActiveProfiles("test");
        ctx.refresh();

//        Map<String, Object> ofType = ctx.getBeansOfType(Object.class);
//        ofType.forEach((s,o) -> System.out.println(s + " :: " + o));

        Controller bean = ctx.getBean(Controller.class);
        bean.doRun();

        WordsDao dao = ctx.getBean(WordsDao.class);
        List<DictionaryWord> dictionaryWords = dao.listWords();
        System.out.println("dictionaryWords = " + dictionaryWords);


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
        @Profile("test")
        public DataSource testDatasource() {
            EmbeddedDatabase ds = new EmbeddedDatabaseBuilder()
//                    .addScript()
                    .setType(EmbeddedDatabaseType.HSQL)
                    .build();
            return ds;
        }

        @Bean
        @Profile("!test")
        public DataSource productionDatasource() {
            DriverManagerDataSource ds = new DriverManagerDataSource();
            ds.setDriverClassName(org.hsqldb.jdbc.JDBCDriver.class.getName());
            ds.setUrl("jdbc:hsqldb:file:/tmp/sandboxdb/");
            ds.setUsername("SA");
            ds.setPassword("");

            return ds;
        }

        @Bean(destroyMethod = "close")
        public WordsDao dbiDao(DataSource ds) {
            // http://jdbi.org/
            DBI dbi = new DBI(ds);
            return dbi.open(WordsDao.class);
        }

    }
}

