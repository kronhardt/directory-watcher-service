package br.com.agibank.directorywatcherservice.stub;

import br.com.agibank.directorywatcherservice.domain.SalesReport;

import java.util.List;

public class SalesReportStub {

    public static SalesReport createOne() {
        return SalesReport.builder()
                .salesmans(List.of(SalesmanStub.createSalesmanFernando(),
                                   SalesmanStub.createSalesmanRafael()))
                .customers(List.of(CustomerStub.createCustomerFelipe(),
                                   CustomerStub.createCustomerMarcus()))
                .sales(List.of(SaleStub.createSaleWithItem55And56(),
                               SaleStub.createSaleWithItem98And99()))
                .build();
    }
}

