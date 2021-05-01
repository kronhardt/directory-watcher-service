package br.com.agibank.directorywatcherservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class DirectoryWatcherServiceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DirectoryWatcherServiceApplication.class, args);
	}
}
