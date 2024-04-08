package com.bus.BusService.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
@Configuration
public class BusServiceConfiguration {

	
	@Bean
	ModelMapper modelMapper(){
		
		return new ModelMapper();
	}
	

}
