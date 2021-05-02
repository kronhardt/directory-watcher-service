package br.com.agibank.directorywatcherservice.stub;

import br.com.agibank.directorywatcherservice.domain.Item;

public class ItemStub {

    public static Item createPriceItem100() {
        return Item.builder()
                .idItem(55L)
                .price(120.00)
                .quantity(1.0)
                .build();
    }

    public static Item createPriceItem200() {
        return Item.builder()
                .idItem(56L)
                .price(200.00)
                .quantity(2.0)
                .build();
    }

    public static Item createPriceItem500() {
        return Item.builder()
                .idItem(98L)
                .price(500.00)
                .quantity(1.0)
                .build();
    }

    public static Item createPriceItem700() {
        return Item.builder()
                .idItem(99L)
                .price(700.00)
                .quantity(2.0)
                .build();
    }
}
