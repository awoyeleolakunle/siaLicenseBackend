package dansarkitechnology.sialicensebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching

public class SialicensebackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(SialicensebackendApplication.class, args);
	}

}
