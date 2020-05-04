package com.cts.fms.eventprocessor;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class EventprocessorApplication {
	public static void main(String[] args) {
		SpringApplication.run(EventprocessorApplication.class, args);
	}
}
