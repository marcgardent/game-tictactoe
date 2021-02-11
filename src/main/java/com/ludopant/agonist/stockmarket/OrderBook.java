package com.ludopant.agonist.stockmarket;
import java.util.ArrayList;
import java.util.List;

public class OrderBook {
    private final List<Order> orders = new ArrayList<Order>();
    private final int timestamp;

    public OrderBook(int timestamp) {
        this.timestamp = timestamp;
    }

    public void append(Order order) {
        orders.add(order);
    }

    public OrderBook prepare(){
        OrderBook next = new OrderBook(timestamp+1);
        for (Order order : orders) {

        }
        return next;
    }
}