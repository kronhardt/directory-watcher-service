package br.com.agibank.directorywatcherservice.mapper;

import br.com.agibank.directorywatcherservice.domain.Sale;
import br.com.agibank.directorywatcherservice.stub.ItemStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SaleMapperTest {

    @Test
    void mustBeSuccessfullyMapSaleData() {

        Sale saleExpected = Sale.builder()
                .idSale(1L)
                .salesmanName("Joao")
                .itemsSale(
                        List.of(ItemStub.createPriceItem100()))
                .build();

        List<String> lineSale = new ArrayList<>();
        lineSale.add("1");
        lineSale.add("55-1-120");
        lineSale.add("Joao");

        SaleMapper saleMapper = new SaleMapper();
        Sale saleActual = saleMapper.mapFrom(lineSale);

        Assertions.assertEquals(saleExpected.getIdSale(), saleActual.getIdSale());
        Assertions.assertEquals(saleExpected.getSalesmanName(), saleActual.getSalesmanName());
        Assertions.assertEquals(saleExpected.getItemsSale().get(0).getIdItem(), saleActual.getItemsSale().get(0).getIdItem());
        Assertions.assertEquals(saleExpected.getItemsSale().get(0).getQuantity(), saleActual.getItemsSale().get(0).getQuantity());
        Assertions.assertEquals(saleExpected.getItemsSale().get(0).getPrice(), saleActual.getItemsSale().get(0).getPrice());
    }
}
