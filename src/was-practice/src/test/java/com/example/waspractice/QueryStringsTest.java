package com.example.waspractice;

import com.example.waspractice.tomcat.QueryStrings;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class QueryStringsTest {
    @Test
    void createTest() {
        QueryStrings queryStrings = new QueryStrings("operand1=11&operand2=55");
        assertThat(queryStrings).isNotNull();
    }
}
