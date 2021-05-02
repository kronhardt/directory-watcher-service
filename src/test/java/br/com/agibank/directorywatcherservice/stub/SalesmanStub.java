package br.com.agibank.directorywatcherservice.stub;

import br.com.agibank.directorywatcherservice.domain.Salesman;

public class SalesmanStub {

    public static Salesman createSalesmanFernando() {
        return Salesman.builder()
                .cpf("02306749094")
                .salary(5000.00)
                .name("Fernando")
                .build();
    }

    public static Salesman createSalesmanRafael() {
        return Salesman.builder()
                .cpf("12658974236")
                .salary(2000.00)
                .name("Rafael")
                .build();
    }
}
