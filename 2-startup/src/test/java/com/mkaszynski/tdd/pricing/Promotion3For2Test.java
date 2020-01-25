package com.mkaszynski.tdd.pricing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

    class Promotion3For2Test {
        @DisplayName("should-return-single-product-with-same-price-when-one-product-in-input")
        @Test
        void oneProduct() {
            Promotion3For2 promotion = new Promotion3For2();
            List<Product> product = (List<Product>) promotion.apply(butter(1));
            assertThat(product).contains(new Product("butter", 10, 1));
        }

        private Product butter(int quantity) {
            return new Product("butter", 229, 1);
        }
    }