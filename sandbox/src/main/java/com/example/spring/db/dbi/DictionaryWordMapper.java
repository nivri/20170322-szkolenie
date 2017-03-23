package com.example.spring.db.dbi;

import com.example.spring.db.DictionaryWord;
import org.skife.jdbi.v2.StatementContext;
import org.skife.jdbi.v2.tweak.ResultSetMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DictionaryWordMapper implements ResultSetMapper<DictionaryWord> {

    @Override
    public DictionaryWord map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new DictionaryWord(resultSet.getString("polish_word"),
                resultSet.getString("english_word"));
    }
}
