package com.ludopant.agonist.events;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public abstract class ObservableBase<F> implements  Observable<F>{

    protected List<F> observers = new ArrayList<F>();

    public void subscribe(F observer){
        observers.add(observer);
    }

    public void unSubscribe(F observer){
        this.observers = observers.stream().filter(x-> x ==observer).collect(Collectors.toList());

    }

    public  Observable<F> asObservable(){ return this; }
}
