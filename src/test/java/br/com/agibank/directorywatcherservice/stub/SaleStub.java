package br.com.agibank.directorywatcherservice.stub;

import br.com.agibank.directorywatcherservice.domain.Sale;

import java.util.List;

public class SaleStub {

    public static Sale createSaleWithItem55And56() {
        return Sale.builder()
                .idSale(1L)
                .salesmanName("Joao")
                .itemsSale(
                        List.of(ItemStub.createPriceItem100(),
                                ItemStub.createPriceItem200()))
                .build();
    }

    public static Sale createSaleWithItem98And99() {
        return Sale.builder()
                .idSale(23L)
                .salesmanName("Maria")
                .itemsSale(
                        List.of(ItemStub.createPriceItem500(),
                                ItemStub.createPriceItem700()))
                .build();
    }
}
