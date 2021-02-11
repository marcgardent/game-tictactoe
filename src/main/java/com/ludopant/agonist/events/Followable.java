package com.ludopant.agonist.events;

import java.util.function.Consumer;

public interface Followable<T> extends  Observable<Consumer<T>>{
    T getValue();
}
