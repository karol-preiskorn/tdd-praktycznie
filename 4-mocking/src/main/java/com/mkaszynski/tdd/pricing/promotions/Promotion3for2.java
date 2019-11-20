package com.mkaszynski.tdd.pricing.promotions;

import com.mkaszynski.tdd.pricing.Product;
import com.mkaszynski.tdd.pricing.Products;

import java.util.List;

public class Promotion3for2 implements Promotion {

    @Override
    public List<Product> apply(Product product) {
        Products productsAfterPromotion = new Products();
        productsAfterPromotion.add(product.samePrice(numberOfSamePriceProducts(product.quantity())));
        productsAfterPromotion.add(product.freeProduct(numberOfFreeProducts(product.quantity())));
        return productsAfterPromotion.asList();
    }

    private int numberOfSamePriceProducts(int quantity) {
        int numberOfFreeProducts = numberOfFreeProducts(quantity);
        return quantity - numberOfFreeProducts;
    }

    private int numberOfFreeProducts(int quantity) {
        return quantity / 3;
    }
}
