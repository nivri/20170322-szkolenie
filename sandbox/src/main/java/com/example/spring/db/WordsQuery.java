package com.example.spring.db;

import org.springframework.jdbc.object.MappingSqlQuery;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class WordsQuery extends MappingSqlQuery<DictionaryWord> {

    public WordsQuery(DataSource ds) {
        super(ds, "select * from words");

    }

    @Override
    protected DictionaryWord mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new DictionaryWord(rs.getString("polish_word"),
                rs.getString("english_word")
        );
    }

}

