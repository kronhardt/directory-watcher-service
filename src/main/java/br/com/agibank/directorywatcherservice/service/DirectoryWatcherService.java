package br.com.agibank.directorywatcherservice.service;

import br.com.agibank.directorywatcherservice.util.DirectoryUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.*;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.util.HashMap;
import java.util.Map;

@Service
public class DirectoryWatcherService {
    private final String enviromentHomePath;
    private final String directoryEntrance;
    private final WatchService watcherService;
    private final Map<WatchKey,Path> keys;
    private final Logger logger;
    private FileProcessorService fileProcessorService;

    @Autowired
    public DirectoryWatcherService(@Value("${env.homepath}") String enviromentHomePath,
                                   @Value("${directory.entrance}") String directoryEntrance,
                                   FileProcessorService fileProcessorService) throws IOException {
        this.enviromentHomePath = enviromentHomePath;
        this.directoryEntrance = directoryEntrance;
        this.watcherService =  FileSystems.getDefault().newWatchService();
        this.keys = new HashMap<>();
        this.logger = LoggerFactory.getLogger(DirectoryWatcherService.class);
        this.fileProcessorService = fileProcessorService;
    }

    @Scheduled(fixedDelay = Long.MAX_VALUE)
    private void startWatcher() throws IOException, InterruptedException {
        Path path = Paths.get(System.getenv(enviromentHomePath).concat(directoryEntrance));
        DirectoryUtil.createDirectoryIfNotExist(path.toString());
        WatchKey key = path.register(watcherService, ENTRY_CREATE);
        keys.put(key, path);
        logger.info("Observando o diretório: {}", path);
        processEvents();
    }

    public void processEvents() throws InterruptedException {
        WatchKey key;
        while ((key = watcherService.take()) != null) {
            Path path = keys.get(key);
            for (WatchEvent<?> event: key.pollEvents()) {
                WatchEvent<Path> watchEvent = cast(event);
                Path fileName = watchEvent.context();
                Path pathWithFileName = path.resolve(fileName);
                logger.info("Recebido evento {}: {}", event.kind().name(), pathWithFileName);
                fileProcessorService.fileProcessor(pathWithFileName.toFile());
            }
            if (!key.reset()) {
                keys.remove(key);
            }
        }
    }
    @SuppressWarnings("unchecked")
    private static <T> WatchEvent<T> cast(WatchEvent<?> event) {
        return (WatchEvent<T>)event;
    }

}