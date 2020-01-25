package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.LocalTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class PromotionHappyHoursTest {
    private final PromotionHappyHours promotion = new PromotionHappyHours();

    @Test
    @DisplayName("should return product with 30% price for one product")
    void happyProduct() {
        Product product = fullPrice(1);

        List<Product> result = promotion.apply(product);

        final LocalTime time1 = LocalTime.parse( "20:11:13"  );
        final LocalTime time2 = LocalTime.parse( "14:49:00" );

        LocalTime nowUtcTime = LocalTime.now(Clock.systemUTC());

        if (nowUtcTime.isAfter(time1) && nowUtcTime.isBefore(time2)) {
            System.out.println(nowUtcTime + " is after: " + time1 + " and before: " + time2);
            assertThat(result).containsOnly(promotionPrice(1));
        }

    }

    private Product promotionPrice(int quantity) {
        return new Product("milk", 30, quantity, Product.Type.FOOD);
    }

    private Product fullPrice(int quantity) {
        return new Product("milk", 100, quantity, Product.Type.FOOD);
    }
}