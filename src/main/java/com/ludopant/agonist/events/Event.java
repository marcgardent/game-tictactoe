package com.ludopant.agonist.events;

public class Event extends  ObservableBase<Runnable> implements EventObservable{

    public void raise() {
        observers.stream().forEach(x-> x.run());
    }

    public EventObservable getEventObservable() { return this; }
}
