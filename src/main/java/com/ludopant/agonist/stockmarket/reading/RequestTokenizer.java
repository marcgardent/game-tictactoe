package com.ludopant.agonist.stockmarket.reading;

import com.ludopant.agonist.util.Option;
import com.ludopant.agonist.util.OptionBuilder;
import com.ludopant.agonist.util.StringUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RequestTokenizer {

    public final static Pattern TOKEN_PATTERN= Pattern.compile("\\$?[a-zA-Z\\-]+|[<=>]{2}|[0-9]+|[,;]|#.*|[ ]+");
    public final static String IGNORE_MATCHES= "#.*|[ ]+";

    public Option<List<Token>> tokenize(String data) {
        OptionBuilder<List<Token>> ret = new OptionBuilder<List<Token>>();
        List<Token> tokens = new ArrayList<Token>();
        Matcher matcher = TOKEN_PATTERN.matcher(data);
        int cursor= 0;
        while (matcher.find()) {
            if (matcher.start()!=cursor){
                return ret.fail(String.format("the tokenizer has found an unexpected token at char %s: ", cursor), StringUtil.outline(data, cursor));
            }
            String value = matcher.group();
            if(!value.matches(IGNORE_MATCHES)){
                tokens.add(new Token(value, matcher.start(), matcher.end()));
            }
            cursor = matcher.end();
        }

        if (data.length()!=cursor){
            return ret.fail(String.format("the tokenizer has found an unexpected token at char %s: ", cursor), StringUtil.outline(data, cursor));
        }

        return ret.success(tokens);
    }


}
