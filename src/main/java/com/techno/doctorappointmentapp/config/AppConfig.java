package com.techno.doctorappointmentapp.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	ModelMapper getModelMapper() {
		return new ModelMapper();
	}
}
