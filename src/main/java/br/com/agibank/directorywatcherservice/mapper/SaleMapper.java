package br.com.agibank.directorywatcherservice.mapper;

import br.com.agibank.directorywatcherservice.domain.Item;
import br.com.agibank.directorywatcherservice.domain.Sale;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class SaleMapper {

    private static final String REGEX_ITEMS = "\\s*,\\s*";

    public Sale mapFrom(List<String> extractedDataRow) {
        return Sale.builder()
                .idSale(Long.valueOf(extractedDataRow.get(0)))
                .itemsSale(buildItemListFromString(extractedDataRow.get(1)))
                .salesmanName(extractedDataRow.get(2))
                .build();
    }

    private List<Item> buildItemListFromString(String salesItem) {
        String[] sales = salesItem.split(REGEX_ITEMS);
        return Arrays.stream(sales)
                .map(this::build)
                .collect(Collectors.toList());
    }

    private Item build(String sale) {
        String[] saleAttributes = sale.split("-");
        return Item.builder()
                .idItem(Long.valueOf(saleAttributes[0]))
                .quantity(Double.valueOf(saleAttributes[1]))
                .price(Double.valueOf(saleAttributes[2]))
                .build();
    }
}
