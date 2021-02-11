package com.ludopant.agonist.stockmarket;

public class Order {

    public final Action type;
    public final int quantity;
    public final Stock stock;
    public final int creationDate;
    public final int latency;

    public Order(Action type, int quantity, Stock stock, int creationDate, int latency) {

        this.type = type;
        this.quantity = quantity;
        this.stock = stock;
        this.creationDate = creationDate;
        this.latency = latency;
    }
}

/*

class Order2 {
    public static final int MAX_VALUE = Short.MAX_VALUE;
    public static final int MIN_VALUE = 1;
    public static final int ASK_NO_LIMIT = MAX_VALUE+1;
    public static final int BID_NO_LIMIT = MIN_VALUE-1;
    public final Stock stock;
    public final Exchange type;
    public final int quantity;
    public final int price;
    public final int startPrice;
    public final int stopPrice;
    public final int timestamp;
    public final int timeout;
    public final int latency;

    private Order(Exchange type, Stock stock, int quantity, int price, int startPrice, int stopPrice, int date, int timeout, int latency) {
        this.type = type;
        this.quantity = quantity;
        this.stock = stock;
        this.price = price;
        this.startPrice = startPrice;
        this.stopPrice = stopPrice;
        this.timestamp = date;
        this.timeout = timeout;
        this.latency = latency;
    }

    public static Validation<Order> BidOrder(Stock stock, int quantity, int price, int startPrice, int stopPrice, int date, int timeout, int latency){
        Order v = new Order(Exchange.BID, stock, quantity, price, startPrice, stopPrice,date,timeout,latency);
        ValidationBuilder<Order> builder = new ValidationBuilder<>(v);
        v.validate(builder);
        v.validateBid(builder);
        return builder.toValidation();
    }

    public static Validation<Order> AskOrder(Stock stock, int quantity, int price, int startPrice, int stopPrice, int date, int timeout, int latency){
        Order v = new Order(Exchange.ASK, stock, quantity, price, startPrice, stopPrice, date, timeout, latency);
        ValidationBuilder<Order> builder = new ValidationBuilder<>(v);
        v.validate(builder);
        v.validateAsk(builder);
        return builder.toValidation();
    }

    private void validateBid(ValidationBuilder<Order> builder){
        builder.is(price== BID_NO_LIMIT || (price < stock.getMarketPrice() && stock.getMarketPrice()<= MAX_VALUE),
                String.format("'%s' is invalid bid price: excepted ]MARKET_PRICE, MAX_VALUE] or NO_LIMIT ", price));
        builder.is(stock.getMarketPrice() < startPrice && startPrice < stopPrice, "BID - TODO Message marketPrice < start < stop");
    }

    private void validateAsk(ValidationBuilder<Order> builder){
        builder.is(price== ASK_NO_LIMIT || (price > stock.getMarketPrice() && stock.getMarketPrice() >= MIN_VALUE),
                String.format("'%s' is invalid ask price: excepted [MIN_VALUE, MARKET_PRICE[ or NO_LIMIT", price));
        builder.is(stock.getMarketPrice() > startPrice && startPrice > stopPrice, "ASK - TODO Message marketPrice > start > stop");
    }

    private void validate(ValidationBuilder<Order> builder){
        builder.is( MIN_VALUE <= quantity  && quantity <= MAX_VALUE, String.format("'%s' is invalid quantity: excepted in the range [MIN_VALUE, MAX_VALUE]", quantity));
        builder.is( BID_NO_LIMIT <= price  && price <= ASK_NO_LIMIT, String.format("'%s' is invalid price: excepted in the range [MIN_VALUE, MAX_VALUE] or equals NO_LIMIT", price));
        builder.is( BID_NO_LIMIT <= startPrice  && startPrice <= ASK_NO_LIMIT, String.format("'%s' is invalid startPrice: excepted in the range [MIN_VALUE, MAX_VALUE] or equals NO_LIMIT", startPrice));
        builder.is( BID_NO_LIMIT <= stopPrice  && stopPrice <= ASK_NO_LIMIT, String.format("'%s' is invalid stopPrice: excepted in the range [MIN_VALUE, MAX_VALUE] or equals NO_LIMIT", stopPrice));
        builder.is( BID_NO_LIMIT <= price  && price <= ASK_NO_LIMIT, String.format("'%s' is invalid price: excepted in the range [MIN_VALUE, MAX_VALUE] or equals NO_LIMIT", price));
        builder.is( timestamp <= timeout  && price <= ASK_NO_LIMIT, String.format("'%s' is invalid timeout: date <= timeout", timeout));
    }
}
*/