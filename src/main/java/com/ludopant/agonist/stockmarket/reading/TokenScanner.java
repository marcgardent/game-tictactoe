package com.ludopant.agonist.stockmarket.reading;

import com.ludopant.agonist.stockmarket.Action;
import com.ludopant.agonist.stockmarket.Stock;
import com.ludopant.agonist.util.Option;
import com.ludopant.agonist.util.OptionBuilder;
import com.ludopant.agonist.util.StringUtil;

import java.util.List;
import java.util.function.Function;

public class TokenScanner {
    private final Function<String, Option<Stock>> stockProvider;
    private final List<Token> tokens;
    private final String source;

    TokenScanner(Function<String, Option<Stock>> stockProvider, List<Token> tokens, String source) {
        this.stockProvider = stockProvider;
        this.tokens = tokens;
        this.source = source;
    }

    boolean hasTokens() {
        return tokens.size() > 0;
    }

    Token next() {
        return tokens.remove(0);
    }

    boolean hasNext() {
        return tokens.size() > 0;
    }

    public Option<Action> nextAction() {
        OptionBuilder<Action> ret = new OptionBuilder<Action>();
        if (!hasNext()) {
            return ret.fail("excepted BUY|SELL, found <EOF>");
        }
        Token next = next();
        if (next.is("BUY")) {
            return ret.success(Action.ASK);
        }
        if (next.is("SELL")) {
            return ret.success(Action.BID);
        }
        return ret.fail(
                String.format("excepted BUY|SELL at char %s, found `%s`", next.start, next.value),
                StringUtil.outline(source, next.start, next.end)
        );
    }

    public Option<Integer> nextInteger(String name) {
        OptionBuilder<Integer> ret = new OptionBuilder<Integer>();
        if (!hasNext()) {
            return ret.fail("excepted <integer>, found <EOF>");
        }
        Token next = next();
        try {
            Integer number = Integer.parseInt(next.value);
            return ret.success(number);
        } catch (Exception e) {
            return ret.fail(String.format("excepted <integer> at char %s, found `%s`", next.start, next.value),
                    StringUtil.outline(source, next.start, next.end));
        }
    }

    public Option<Stock> nextStock() {
        OptionBuilder<Stock> ret = new OptionBuilder<Stock>();
        if (!hasNext()) {
            return ret.fail("excepted <stock id: string>, found <EOF>");
        }
        Token next = next();
        return stockProvider.apply(next.value);
    }
}
