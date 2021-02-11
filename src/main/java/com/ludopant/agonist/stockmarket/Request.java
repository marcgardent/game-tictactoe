package com.ludopant.agonist.stockmarket;

public class Request {
    public final Trader owner;
    public final int Id;
    private Order order;

    public Request(int id, Trader owner, Order order) {
        this.owner = owner;
        Id = id;
        this.order = order;
    }
}
