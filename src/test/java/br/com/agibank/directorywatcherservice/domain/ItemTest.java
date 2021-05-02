package br.com.agibank.directorywatcherservice.domain;

import br.com.agibank.directorywatcherservice.stub.SaleStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ItemTest {

    @Test
    void shouldCalculeteItemsAmountWithAReturnPriceOf520() {
        Sale sale = SaleStub.createSaleWithItem55And56();

        Assertions.assertEquals(520, sale.getItemsAmount());
    }
}
