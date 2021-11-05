package dev.freelance.freeserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication
public class FreeserveApplication {

	public static void main(String[] args) {
		SpringApplication.run(FreeserveApplication.class, args);
	}

}
