package com.example.spring.db;

import com.example.spring.Sandbox;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Sandbox.Cfg.class)
@ActiveProfiles("test")
public class WordsQueryTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Autowired
    WordsQuery query;

    @Test
    public void should_throw_exception_not_object_found() {
        exception.expect(BadSqlGrammarException.class);
        query.execute();
    }

}