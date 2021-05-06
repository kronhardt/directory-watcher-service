package br.com.agibank.directorywatcherservice.service;

import br.com.agibank.directorywatcherservice.domain.SalesReport;
import br.com.agibank.directorywatcherservice.util.DirectoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.PrintWriter;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Service
public class ReportService {

    private final Logger logger = LoggerFactory.getLogger(ReportService.class);
    private final String directoryOutput;
    private final String enviromentHomePath;

    public ReportService(@Value("${directory.output}") String directoryOutput,
                          @Value("${env.homepath}") String enviromentHomePath) {
        this.directoryOutput = directoryOutput;
        this.enviromentHomePath = enviromentHomePath;
    }

    public void createSalesReport(String fileName, SalesReport salesReport) {
        String path = System.getenv(enviromentHomePath).concat(directoryOutput);
        DirectoryUtil.createDirectoryIfNotExist(path);
        String fullPath = path + fileName.replace(".dat", ".done.dat");
        logger.info("Exportando análise dos dados de vendas para: {}", fullPath);
        try (PrintWriter writer = new PrintWriter(fullPath)) {
            String time= new SimpleDateFormat("dd MMM yyyy HH:mm:ss").format(Calendar.getInstance().getTime());
            writer.println(MessageFormat.format("Resultados para {0} - {1}", fullPath, time));
            writer.println("-------------------------------------------------------------------------------------------");
            writer.println(MessageFormat.format("Quantidade de clientes: {0}", salesReport.countCustomer()));
            writer.println(MessageFormat.format("Quantidade de vendedores: {0}", salesReport.countSalesman()));
            writer.println(MessageFormat.format("ID da venda mais cara: {0}", salesReport.getMostExpansiveSale()));
            writer.println(MessageFormat.format("O pior vendedor: {0}", salesReport.getLowestSale()));
        } catch (Exception e) {
            logger.error("Ocorreu um erro ao exportar arquivo de análise de vendas ", e);
        }
        logger.info("Arquivo de análise de vendas exportado com sucesso.");
    }
}
