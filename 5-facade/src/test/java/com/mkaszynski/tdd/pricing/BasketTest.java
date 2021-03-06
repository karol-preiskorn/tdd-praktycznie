package com.mkaszynski.tdd.pricing;

import com.mkaszynski.tdd.pricing.promotions.Promotion3for2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.assertj.core.api.Assertions.assertThat;

class BasketTest {

    @DisplayName("empty basket, when product is added, there is 1 product in basket")
    @Test
    void addProduct() {
        Basket basket = emptyBasket(Campaign.emptyCampaign());

        basket.add(butter());

        assertThat(basket.products()).containsOnly(butter());
    }

    @DisplayName("1 butter in basket, when 2 butters are added, there are 3 butters in basket")
    @Test
    void addProduct_basketNotEmpty() {
        Basket basket = basketWith(butter());

        basket.add(butter(2));

        assertThat(basket.products()).containsOnly(butter(3));
    }

    @DisplayName("1 free butter in basket, when 2 butters are added, there is 1 free butter and 2 full price")
    @Test
    void addProduct_basketWithFreeItem() {
        Basket basket = basketWith(freeButter(1));

        basket.add(butter(2));

        assertThat(basket.products()).containsOnly(freeButter(1), butter(2));
    }

    @DisplayName("empty basket, when 3 butters are added and there is '3 for 2 promotion' on butter, there are 2 full price butters in the basket and 1 free")
    @Test
    void addProduct_3for2campaignOnButter() {
        Basket basket = emptyBasket(butterCampaign());

        basket.add(butter(3));

        assertThat(basket.products()).containsOnly(butter(2), freeButter(1));
    }

    @DisplayName("empty basket, when 3 beers are added and there is '3 for 2 promotion' on butter, there are 3 full price beers in the basket")
    @Test
    void addBeerProduct_3for2campaignOnButter() {
        Basket basket = emptyBasket(butterCampaign());

        basket.add(beer(3));

        assertThat(basket.products()).containsOnly(beer(3));
    }


    private Campaign butterCampaign() {
        return new Campaign("Butter", new Promotion3for2());
    }

    private Basket emptyBasket(Campaign campaign) {
        return new Basket(1L, Collections.emptyList(), campaign);
    }

    private Basket basketWith(SelectedProduct... products) {
        return new Basket(1L, Arrays.asList(products), Campaign.emptyCampaign());
    }

    private SelectedProduct butter() {
        return butter(1);
    }

    private SelectedProduct butter(int quantity) {
        return new SelectedProduct("Butter", 220, quantity, SelectedProduct.Type.FOOD);
    }

    @SuppressWarnings("SameParameterValue")
    private SelectedProduct beer(int quantity) {
        return new SelectedProduct("Beer", 450, quantity, SelectedProduct.Type.LIQUID);
    }

    @SuppressWarnings("SameParameterValue")
    private SelectedProduct freeButter(int quantity) {
        return new SelectedProduct("Butter", 0, quantity, SelectedProduct.Type.FOOD);
    }
}