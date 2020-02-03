package com.example.OAuthApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * エントリーポイント
 */
@SpringBootApplication
@EnableAutoConfiguration
public class OAuthAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(OAuthAppApplication.class, args);
	}

}
