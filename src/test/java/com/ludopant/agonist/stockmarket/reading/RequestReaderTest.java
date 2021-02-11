package com.ludopant.agonist.stockmarket.reading;

import com.ludopant.agonist.stockmarket.Action;
import com.ludopant.agonist.stockmarket.Order;
import com.ludopant.agonist.stockmarket.Stock;
import com.ludopant.agonist.util.Option;
import com.ludopant.agonist.util.OptionBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RequestReaderTest {

    @Test
    void reader_when_failed() {
        RequestReader target = new RequestReader((String id)-> new OptionBuilder<Stock>().success(new Stock()));
        Option<List<Order>> actual = target.fromString("ASK 100 Forbidden");
        for(String s : actual.getReasons()){ System.out.println(s); }
    }

    @Test
    void reader_when_MarketBuyOrder() {
        RequestReader target = new RequestReader((String id)-> new OptionBuilder<Stock>().success(new Stock()));
        Option<List<Order>> actual = target.fromString("ASK 100 Identifier");
        Assertions.assertTrue(actual.isValid());
        List<Order> orders = actual.getValue();
        Assertions.assertEquals(1, orders.size());
        Order order = orders.get(0);
        Assertions.assertEquals(Action.ASK, order.type);
        Assertions.assertEquals(100, order.quantity);
    }
}
