package com.mkaszynski.tdd.pricing;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.Clock;
import java.time.LocalTime;
import java.util.Date;

@ToString
@EqualsAndHashCode
class Product {
    private final String name;
    private final int price;
    private final int quantity;
    private final Type type;

    Product(String name, int price, int quantity, Type type) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.type = type;
    }

    int price() {
        return price;
    }

    String name() {
        return name;
    }

    int quantity() {
        return quantity;
    }

    Type type() {
        return type;
    }

    Product samePrice(int quantity) {
        return new Product(name, price, quantity, type);
    }

    Product freeProduct(int quantity) {
        return new Product(name, 0, quantity, type);
    }

    Product HappyProduct(int quantity) {
        final LocalTime time1 = LocalTime.parse( "20:11:13"  );
        final LocalTime time2 = LocalTime.parse( "14:49:00" );

        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());

        if (nowUtcTime.isAfter(time1) && nowUtcTime.isBefore(time2)){
            System.out.println(nowUtcTime+" is after: "+ time1+" and before: "+ time2);
            return new Product(name, price*30/100, quantity, type);
        }

        return new Product(name, price, quantity, type);
    }

    enum Type {
        LIQUID,
        FOOD
    }
}
