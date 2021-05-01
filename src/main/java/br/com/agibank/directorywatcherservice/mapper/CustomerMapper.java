package br.com.agibank.directorywatcherservice.mapper;

import br.com.agibank.directorywatcherservice.domain.Customer;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerMapper {

    public Customer mapFrom(List<String> extractedDataRow) {
        return Customer.builder()
                .cnpj(extractedDataRow.get(0))
                .name(extractedDataRow.get(1))
                .area(extractedDataRow.get(2))
                .build();
    }
}
