package com.example.dictionary.repository;

import com.example.dictionary.translation.DictionaryWord;
import com.sun.javafx.UnmodifiableArrayList;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@Primary
public class DatabaseRepository implements Repository {

    final private DataSource ds;

    public DatabaseRepository(DataSource ds) {
        this.ds = ds;
    }

    @Override
    public List<DictionaryWord> getSavedWords() {
        JdbcTemplate jdbc = new JdbcTemplate(ds);
        return jdbc.query("select * from words", (rs, rowNum) ->
                new DictionaryWord(rs.getString("polish_word"), rs.getString("english_word")));
    }

    @Override
    public void addWord(DictionaryWord word) {
        Map<String, String> args = new HashMap<>();
        args.put("polish_word", word.polishWord);
        args.put("english_word", word.englishWord);
        SimpleJdbcInsert insert = new SimpleJdbcInsert(ds).withTableName("words");
        insert.execute(args);
    }

    @Override
    public void printSavedWords() {
        getSavedWords().forEach(System.out::println);
    }
}
