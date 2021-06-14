package com.quane.reddit_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class RedditAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedditAppApplication.class, args);
	}

}
