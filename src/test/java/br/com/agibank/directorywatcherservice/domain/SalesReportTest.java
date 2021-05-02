package br.com.agibank.directorywatcherservice.domain;

import br.com.agibank.directorywatcherservice.stub.SalesReportStub;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class SalesReportTest {

    @Test
    void shouldReturnTheMostExpansiveSale() {
        SalesReport salesReport = SalesReportStub.createOne();

        Assertions.assertEquals(23L, salesReport.getMostExpansiveSale());
    }

    @Test
    void shouldReturnLowestSale() {
        SalesReport salesReport = SalesReportStub.createOne();

        Assertions.assertEquals("Joao", salesReport.getLowestSale());
    }

}
