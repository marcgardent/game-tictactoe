package com.ludopant.agonist.events;

import java.util.function.Consumer;

public interface SubjectObservable<F> extends  Observable<Consumer<F>> { }
