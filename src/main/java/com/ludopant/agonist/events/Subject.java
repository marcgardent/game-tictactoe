package com.ludopant.agonist.events;
import java.util.function.Consumer;
import java.util.function.IntFunction;
import java.util.stream.Collectors;


public class Subject<T> extends  ObservableBase<Consumer<T>> implements SubjectObservable<T>{

    public void next(T value) {

        for (Consumer<T> x : observers.stream().collect(Collectors.toList())){
            x.accept(value);
        }
    }

    public SubjectObservable<T> asSubjectObservable() { return this; }
}