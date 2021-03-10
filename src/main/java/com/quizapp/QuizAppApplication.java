package com.quizapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class QuizAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(QuizAppApplication.class, args);


	}
	/**
	 I'll leave that for future tests
	 */
	@EventListener(ApplicationReadyEvent.class)
	public void testApplication(){

	}

}
