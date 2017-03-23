package com.example.dictionary;

import com.example.dictionary.validation.SearchValidationGroup;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class GenericParamsTest {

    @Test
    public void should_create_search_params() {

        //given
        Params params = GenericParams.ofString("search book");

        //then
        assertThat(params.getCommand(), equalTo("search"));
        assertThat(params.getAttrs(), hasItem("book"));
        assertThat(params.validationGroup(), equalTo(SearchValidationGroup.class));

    }

}