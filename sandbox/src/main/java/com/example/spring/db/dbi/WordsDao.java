package com.example.spring.db.dbi;

import com.example.spring.db.DictionaryWord;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

import java.util.List;

public interface WordsDao {

    @SqlQuery("select * from words")
    @Mapper(DictionaryWordMapper.class)
    List<DictionaryWord> listWords();

    void close();
}
