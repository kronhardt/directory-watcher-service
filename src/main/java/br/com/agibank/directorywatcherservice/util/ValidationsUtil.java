package br.com.agibank.directorywatcherservice.util;

public class ValidationsUtil {

    public static boolean isFileExtensionExpected(String fileName, String fileExtension) {
        return fileName.toLowerCase().endsWith(fileExtension);
    }

    public static boolean isEntryCreateEvent(String name) {
        return name.equals("ENTRY_CREATE");
    }

    public static boolean isValidCpf(String cpf) {
        return cpf.length() == 11;
    }

    public static boolean isValidCnpj(String cnpj) {
        return cnpj.length() == 14;
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
