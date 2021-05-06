package br.com.agibank.directorywatcherservice.service;

import br.com.agibank.directorywatcherservice.mapper.CustomerMapper;
import br.com.agibank.directorywatcherservice.mapper.SaleMapper;
import br.com.agibank.directorywatcherservice.mapper.SalesmanMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.File;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


class FileProcessorServiceTest {

    private ReportService reportService;
    private FileProcessorService fileProcessorService;
    private SalesmanMapper salesmanMapper;
    private CustomerMapper customerMapper;
    private SaleMapper saleMapper;
    private static final String REGEX_SALESMAN = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒ" +
            "ÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})(?:ç)([0-9\\.\\,]{1,})";
    private static final String REGEX_SALE_ITEMS = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)(?:\\[)([\\d\\-\\,\\.\\]0-9]{" +
            "1,})(?:\\])(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})";
    private static final String REGEX_CUSTOMER = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒ" +
            "ÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ^ç]{1,})(?:[ç])([A-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})";

    @BeforeEach
    void setup() {
        this.reportService = mock(ReportService.class);
        this.salesmanMapper = mock(SalesmanMapper.class);
        this.customerMapper = mock(CustomerMapper.class);
        this.saleMapper = mock(SaleMapper.class);

        fileProcessorService = new FileProcessorService(
                "HOME",
                "data/in",
                reportService,
                salesmanMapper,
                customerMapper,
                saleMapper);
    }

    @Test
    void shouldNotProcessWhenFileIsDifferentFromDat() {
        File file = new File("src/test/resources/test.txt");

        fileProcessorService.fileProcessor(file);

        Mockito.verify(reportService, Mockito.times(0)).createSalesReport(any(), any());
    }

    @Test
    void shouldNotProcessWithLineIdentifierInvalid() {
        File file = new File("src/test/resources/testIdentifierInvalid.dat");

        fileProcessorService.fileProcessor(file);

        Mockito.verify(salesmanMapper, Mockito.times(0)).mapFrom(any());
        Mockito.verify(customerMapper, Mockito.times(0)).mapFrom(any());
        Mockito.verify(saleMapper, Mockito.times(0)).mapFrom(any());
    }

    @Test
    void shouldProcessWithLineIdentifierValid() {
        File file = new File("src/test/resources/test.dat");

        fileProcessorService.fileProcessor(file);

        Mockito.verify(salesmanMapper, Mockito.times(2)).mapFrom(any());
        Mockito.verify(customerMapper, Mockito.times(2)).mapFrom(any());
        Mockito.verify(saleMapper, Mockito.times(2)).mapFrom(any());
    }

    @Test
    void mustBeSuccessfullyProcessedTheNameSalesmanWithCedilla() {
        String salesmanNameWithCedilla = "001ç1234567891234çPedro Assunçãoç50000";

        List<String> extractedDataRow = fileProcessorService.getDataRow(salesmanNameWithCedilla, REGEX_SALESMAN);

        Assertions.assertEquals("Pedro Assunção", extractedDataRow.get(1));
    }

    @Test
    void mustBeSuccessfullyProcessedTheNameCustomerWithCedilla() {
        String salesmanNameWithCedilla = "002ç2345675434544345çMaria da ConceiçãoçRural";

        List<String> extractedDataRow = fileProcessorService.getDataRow(salesmanNameWithCedilla, REGEX_CUSTOMER);

        Assertions.assertEquals("Maria da Conceição", extractedDataRow.get(1));
    }

    @Test
    void mustBeSuccessfullyProcessedTheNameSaleWithCedilla() {
        String salesmanNameWithCedilla = "003ç10ç[1-10-100,2-30-2.50,3-40-3.10]çGilberto Gonçalves";

        List<String> extractedDataRow = fileProcessorService.getDataRow(salesmanNameWithCedilla, REGEX_SALE_ITEMS);

        Assertions.assertEquals("Gilberto Gonçalves", extractedDataRow.get(2));
    }

}
