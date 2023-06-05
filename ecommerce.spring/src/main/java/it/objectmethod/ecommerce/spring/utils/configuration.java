package it.objectmethod.ecommerce.spring.utils;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
public class configuration {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}
}
