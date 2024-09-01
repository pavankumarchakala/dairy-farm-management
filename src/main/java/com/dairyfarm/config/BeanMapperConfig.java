package com.dairyfarm.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@Configuration
public class BeanMapperConfig {

	@Bean
	public ModelMapper configureModelMapper() {
		ModelMapper mapper = new ModelMapper();
		mapper.getConfiguration().setSkipNullEnabled(true);
		// TODO: Configure if needed
		return mapper;
	}

}
