package com.ludopant.agonist.stockmarket.policies;

import com.ludopant.agonist.stockmarket.Order;
import java.util.function.Function;
import java.util.function.Supplier;

public class PolicyBuilder {

}

class ExpirationPolicy {

    public Function<Order,Boolean> returnFalse(){
        return (Order o) -> false;
    }

    public Function<Order,Boolean> returnTrue(){
        return (Order o) -> true;
    }

    public Function<Order,Boolean> liveDuring(Supplier<Integer> now, int timeSpan){
        return (Order o) -> now.get() > (o.creationDate + timeSpan);
    }

    public Function<Order,Boolean> when_A_GE_B(Supplier<Integer> a, Supplier<Integer> b){
        return (Order o) -> a.get() >= b.get();
    }

    public Function<Order,Boolean> when_A_LE_B(Supplier<Integer> a, Supplier<Integer> b){
        return (Order o) -> a.get() <= b.get();
    }
}
