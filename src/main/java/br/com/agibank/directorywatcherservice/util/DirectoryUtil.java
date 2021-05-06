package br.com.agibank.directorywatcherservice.util;

import java.io.File;

public class DirectoryUtil {

    public static void createDirectoryIfNotExist(String path) {
        File dir = new File(path);
        if (!dir.exists()) {
            dir.mkdirs();
        }
    }
}