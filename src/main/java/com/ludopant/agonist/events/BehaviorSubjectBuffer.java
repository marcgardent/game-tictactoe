package com.ludopant.agonist.events;

import java.util.concurrent.ConcurrentLinkedDeque;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class BehaviorSubjectBuffer<T> implements Followable<T>{

    private ConcurrentLinkedQueue<T> buffer = new ConcurrentLinkedQueue<T>();

    public final BehaviorSubject<T> behaviorSubject;

    public BehaviorSubjectBuffer(T v){
        behaviorSubject = new BehaviorSubject<>(v);
    }

    @Override
    public T getValue() {
        return behaviorSubject.getValue();
    }

    @Override
    public void subscribe(Consumer<T> observer) {
        behaviorSubject.subscribe(observer);
    }

    @Override
    public void unSubscribe(Consumer<T> observer) {
        behaviorSubject.unSubscribe(observer);
    }

    public Followable<T> asFollowable(){ return this; }


    public void next(T value) {
        buffer.offer(value);
    }

    public void dispatch(){
        for(T v = buffer.poll();v!=null;v = buffer.poll()){
            behaviorSubject.next(v);
        }
    }
}
