package br.com.agibank.directorywatcherservice.service;


import br.com.agibank.directorywatcherservice.domain.*;
import br.com.agibank.directorywatcherservice.mapper.CustomerMapper;
import br.com.agibank.directorywatcherservice.mapper.SaleMapper;
import br.com.agibank.directorywatcherservice.mapper.SalesmanMapper;
import br.com.agibank.directorywatcherservice.util.DirectoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static br.com.agibank.directorywatcherservice.util.ValidationsUtil.*;

@Service
public class FileProcessorService {

    private SalesReport salesReport;
    private final String enviromentHomePath;
    private final String directoryEntrance;
    private final ReportService reportService;
    private final SalesmanMapper salesmanMapper;
    private final CustomerMapper customerMapper;
    private final SaleMapper saleMapper;
    private static final String DAT_EXTENSION = ".dat";
    private final Logger logger = LoggerFactory.getLogger(FileProcessorService.class);
    private static final String REGEX_SALESMAN = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒ" +
            "ÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})(?:ç)([0-9\\.\\,]{1,})";
    private static final String REGEX_SALE_ITEMS = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)(?:\\[)([\\d\\-\\,\\.\\]0-9]{" +
            "1,})(?:\\])(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})";
    private static final String REGEX_CUSTOMER = "(?:^00[1-3])(?:ç)([0-9]{1,})(?:ç)([a-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒ" +
            "ÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ^ç]{1,})(?:[ç])([A-zA-Z\\sZéúíóáÉÚÍÓÁèùìòàÈÙÌÒÀõãñÕÃÑêûîôâÊÛÎÔÂëÿüïöäËYÜÏÖÄçÇ]{1,})";

    @Autowired
    public FileProcessorService(@Value("${env.homepath}") String enviromentHomePath,
                                @Value("${directory.entrance}") String directoryEntrance,
                                @Autowired ReportService reportService,
                                @Autowired SalesmanMapper salesmanMapper,
                                @Autowired CustomerMapper customerMapper,
                                @Autowired SaleMapper saleMapper) {
        this.enviromentHomePath = enviromentHomePath;
        this.directoryEntrance = directoryEntrance;
        this.reportService = reportService;
        this.salesmanMapper = salesmanMapper;
        this.customerMapper = customerMapper;
        this.saleMapper = saleMapper;
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    private void initialFileProcessor() {
        Path path = Paths.get(System.getenv(enviromentHomePath).concat(directoryEntrance));
        DirectoryUtil.createDirectoryIfNotExist(path.toString());
        File dir = new File(path.toString());
        File[] listFiles = dir.listFiles();
        if (listFiles != null) {
            for (File file : listFiles) {
                fileProcessor(file);
            }
        }
    }

    public void fileProcessor(File file) {
        if (isFileExtensionExpected(file.getName(), DAT_EXTENSION)) {
            try {
                salesReport = new SalesReport();
                readFile(file.getAbsolutePath());
                reportService.createSalesReport(file.getName(), salesReport);
            } catch (Exception e) {
                logger.error("Ocorreu um erro inesperado ao processar o arquivo {}:", file.getAbsolutePath(), e);
            }
        } else {
            logger.info("Arquivo {} não é de extensão '.dat', descartando...", file.getAbsolutePath());
        }
    }

    private void readFile(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (isSalesman(line)) {
                    processSalesman(line);
                } else if (isCustomer(line)) {
                    processCustomer(line);
                } else if (isSale(line)) {
                    processSale(line);
                } else {
                    logger.error("O identificador da linha não é válido, descartando...");
                }
            }
        } catch (Exception e) {
            logger.error("Ocorreu um erro inesperado tentar ler o arquivo {}: ", fileName, e);
        }
    }

    private void processSalesman(String line) {
        List<String> extractedDataRow = getDataRow(line, REGEX_SALESMAN);
        if (!extractedDataRow.isEmpty()) {
            Salesman salesman = salesmanMapper.mapFrom(extractedDataRow);
            salesReport.addData(salesman);
        }
    }

    private void processCustomer(String line) {
        List<String> extractedDataRow = getDataRow(line, REGEX_CUSTOMER);
        if (!extractedDataRow.isEmpty() ) {
            Customer customer = customerMapper.mapFrom(extractedDataRow);
            salesReport.addData(customer);
        }
    }

    private void processSale(String line) {
        List<String> extractedDataRow = getDataRow(line, REGEX_SALE_ITEMS);
        if (!extractedDataRow.isEmpty()) {
            Sale sale = saleMapper.mapFrom(extractedDataRow);
            salesReport.addData(sale);
        }
    }

    public List<String> getDataRow(String line, String regex) {
        final Pattern pattern = Pattern.compile(regex);
        final Matcher matcher = pattern.matcher(line);
        List<String> extractedDataRow  = new ArrayList<>();
        if (matcher.groupCount() == 3) {
            while (matcher.find()) {
                for (int i = 1; i <= matcher.groupCount(); i++) {
                    extractedDataRow.add(matcher.group(i));
                }
            }
        } else {
            logger.error("Dados da linha fora do padrão esperado, descartando...");
        }
        return extractedDataRow;
    }
}