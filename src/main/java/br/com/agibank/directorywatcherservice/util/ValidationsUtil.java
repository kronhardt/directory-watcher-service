package br.com.agibank.directorywatcherservice.util;

public class ValidationsUtil {

    public static boolean isFileExtensionExpected(String fileName, String fileExtension) {
        return fileName.toLowerCase().endsWith(fileExtension);
    }

    public static boolean isSalesman(String line) {
        return line.startsWith("001");
    }

    public static boolean isCustomer(String line) {
        return line.startsWith("002");
    }

    public static boolean isSale(String line) {
        return line.startsWith("003");
    }
}
