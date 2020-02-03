package com.example.apiApp;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;

/**
 * エントリーポイント
 */
@SpringBootApplication
@EnableScheduling
@EntityScan(basePackageClasses = {ApiAppApplication.class, Jsr310JpaConverters.class})
public class ApiAppApplication{
	public static void main(String[] args) {
		SpringApplication.run(ApiAppApplication.class, args);
	}
}
