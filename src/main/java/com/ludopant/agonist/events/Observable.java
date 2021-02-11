package com.ludopant.agonist.events;

public interface Observable<F>{
    void subscribe(F observer);
    void unSubscribe(F observer);
}





