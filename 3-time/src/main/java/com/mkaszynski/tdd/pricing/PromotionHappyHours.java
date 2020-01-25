package com.mkaszynski.tdd.pricing;

import java.util.List;

public class PromotionHappyHours implements Promotion {
    @Override
    public List<Product> apply(Product product) {
        Products productsAfterPromotion = new Products();
        productsAfterPromotion.add(product.HappyProduct(product.quantity()));
        return productsAfterPromotion.asList();
    }
}
