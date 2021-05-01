package br.com.agibank.directorywatcherservice.mapper;

import br.com.agibank.directorywatcherservice.domain.Salesman;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

@Component
public class SalesmanMapper {

    private final Logger logger = LoggerFactory.getLogger(SalesmanMapper.class);

    public Salesman mapFrom(List<String> extractedDataRow) {
        return Salesman.builder()
                .cpf(extractedDataRow.get(0))
                .name(extractedDataRow.get(1))
                .salary(new BigDecimal(extractedDataRow.get(2)))
                .build();
    }
}
