package com.ludopant.agonist.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class OptionBuilder<T> implements Option<T> {
    private T value;
    public final List<String> reasons = new ArrayList<>();


    public void is(boolean exp, String message) {
        if (!exp) {
            reasons.add(message);
        }
    }

    public void isNot(boolean exp, String message) {
        if (exp) {
            reasons.add(message);
        }
    }

    @Override
    public List<String> getReasons() {
        return reasons;
    }

    @Override
    public boolean isValid() {
        return reasons.size() == 0;
    }

    @Override
    public T getValue() {
        return value;
    }

    public Option<T> done(T value) {
        this.value = value;
        return this;
    }

    public Option<T> success(T value) {
        this.value = value;
        return this;
    }

    public Option<T> fail(String... messages) {
        reasons.addAll(Arrays.asList(messages));
        return this;
    }

    public Option<T> raise(Validation validation) {
        reasons.addAll(validation.getReasons());
        return this;
    }

    public void mergeFirstFailed(Validation... validations) {
        for (Validation validation:
             validations) {
            if(!validation.isValid()){
                reasons.addAll(validation.getReasons());
                break;
            }
        }
    }
    public void mergeAllfailed(Validation... validations) {
        for (Validation validation:
                validations) {
            if(!validation.isValid()){
                reasons.addAll(validation.getReasons());
            }
        }
    }
}
