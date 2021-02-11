package com.ludopant.agonist.stockmarket.reading;
import com.ludopant.agonist.stockmarket.*;
import com.ludopant.agonist.util.Option;
import com.ludopant.agonist.util.OptionBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class RequestReader {

    private final Function<String, Option<Stock>> stockByName;

    public RequestReader(Function<String, Option<Stock>> stockByName){

        this.stockByName = stockByName;
    }

    public Option<List<Order>> fromString(String statement) {
        OptionBuilder<List<Order>> ret = new OptionBuilder<List<Order>>();
        Option<List<Token>> tokens = new RequestTokenizer().tokenize(statement);
        if(tokens.isValid()){
            TokenScanner ctx = new TokenScanner(stockByName, tokens.getValue(), statement);
            RequestParser parser = new RequestParser(ctx);
            return parser.parseOrderList();
        }
        else {
            return ret.raise(tokens);
        }
    }
}


