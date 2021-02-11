package com.ludopant.agonist.stockmarket.reading;

public class Token {

    public final String value;
    public final int start;
    public final int end;

    Token(String value, int start, int end) {
        this.value = value;
        this.start = start;
        this.end = end;
    }

    public boolean is(String value) {
        return value.equals(value);
    }
}
