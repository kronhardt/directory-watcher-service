package br.com.agibank.directorywatcherservice;

import br.com.agibank.directorywatcherservice.service.DirectoryWatcherService;
import br.com.agibank.directorywatcherservice.service.FileProcessorService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest
@MockBean(FileProcessorService.class)
@MockBean(DirectoryWatcherService.class)
class DirectoryWatcherServiceApplicationTests {

	@Test
	void contextLoads() {
	}

}
