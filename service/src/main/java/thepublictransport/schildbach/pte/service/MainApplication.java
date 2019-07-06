package thepublictransport.schildbach.pte.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import thepublictransport.schildbach.pte.service.framework.cache.CacheManagement;
import thepublictransport.schildbach.pte.service.framework.cache.GarbageCollector;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
@EnableAutoConfiguration
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);

		GarbageCollector.INSTANCE.emptyCachePeriodically();
	}

}
