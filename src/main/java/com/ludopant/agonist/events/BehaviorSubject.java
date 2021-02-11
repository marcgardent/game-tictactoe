package com.ludopant.agonist.events;


public class BehaviorSubject<T> extends Subject<T> implements Followable<T> {

    public BehaviorSubject(T value) {
        super();
        this.value = value;
    }

    private T value;

    public void  setValue(T value){
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    @Override
    public void next(T value) {
        setValue(value);
        super.next(value);
    }

    public Followable<T> asFollowable(){ return this; }
}