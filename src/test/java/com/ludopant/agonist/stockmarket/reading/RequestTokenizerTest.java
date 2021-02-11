package com.ludopant.agonist.stockmarket.reading;

import com.ludopant.agonist.util.Option;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class RequestTokenizerTest {

    @Test
    void tokenizer() {
        String input = "TEST $Test >= 10, Plop-Plop; #Comment comment";

        RequestTokenizer target = new RequestTokenizer();
        Option<List<Token>> tokens = target.tokenize(input);
        for(String s : tokens.getReasons()){ System.out.println(s); }
        Assertions.assertTrue(tokens.isValid());
        Object[] values = tokens.getValue().stream().map((x)->x.value).toArray();
        for(Object s : values){ System.out.println(s); }
        Assertions.assertArrayEquals(values, new String[]{"TEST", "$Test", ">=", "10", ",", "Plop-Plop", ";"});
    }

    @Test
    void tokenizer_when_failed() {
        String input = "0123456789*";

        RequestTokenizer target = new RequestTokenizer();
        Option<List<Token>> tokens = target.tokenize(input);
        for(String s : tokens.getReasons()){ System.out.println(s); }
        Assertions.assertFalse(tokens.isValid());

    }
}