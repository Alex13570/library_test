package ru.zakhrey.library_test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class LibraryTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryTestApplication.class, args);
	}

}
