package com.ludopant.agonist.stockmarket.reading;

import com.ludopant.agonist.stockmarket.Action;
import com.ludopant.agonist.stockmarket.Order;
import com.ludopant.agonist.stockmarket.Stock;
import com.ludopant.agonist.util.Option;
import com.ludopant.agonist.util.OptionBuilder;

import java.util.ArrayList;
import java.util.List;

public class RequestParser {

    private final TokenScanner ctx;
    RequestParser(TokenScanner ctx) {
        this.ctx = ctx;
    }

    public Option<List<Order>> parseOrderList() {
        OptionBuilder<List<Order>> ret = new OptionBuilder<List<Order>>();
        List<Order> payload = new ArrayList<>();

        while (ctx.hasNext()) {
            Option<Order> order = parseOrder();
            if (order.isValid()) {
                payload.add(order.getValue());
            } else {
                return ret.raise(order);
            }
        }
        return ret.success(payload);
    }

    public Option<Order> parseOrder() {
        OptionBuilder<Order> ret = new OptionBuilder<Order>();
        Option<Action> action = ctx.nextAction();
        Option<Integer> quantity = ctx.nextInteger("quantity");
        Option<Stock> stock = ctx.nextStock();
        ret.mergeFirstFailed(action, quantity, stock);
        if (ret.isValid()) {
            Order order = new Order(action.getValue(), quantity.getValue(), stock.getValue(), 0, 0);
            return ret.success(order);
        } else {
            return ret.fail("Invalid order statement: read above");
        }
    }
}
