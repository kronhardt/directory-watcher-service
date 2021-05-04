package br.com.agibank.directorywatcherservice.mapper;

import br.com.agibank.directorywatcherservice.domain.Salesman;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class SalesmanMapperTest {

    @Test
    void mustBeSuccessfullyMapSaleData() {

        Salesman salesmanExpected = Salesman.builder()
                .name("Fernando")
                .cpf("02306749094")
                .salary(5000.00)
                .build();

        List<String> lineSalesman = new ArrayList<>();
        lineSalesman.add("02306749094");
        lineSalesman.add("Fernando");
        lineSalesman.add("5000.00");

        SalesmanMapper salesmanMapper = new SalesmanMapper();
        Salesman salesmanActual = salesmanMapper.mapFrom(lineSalesman);

        Assertions.assertEquals(salesmanExpected.getCpf(), salesmanActual.getCpf());
        Assertions.assertEquals(salesmanExpected.getName(), salesmanActual.getName());
        Assertions.assertEquals(salesmanExpected.getSalary(), salesmanActual.getSalary());
    }
}
