package com.dairyfarm;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

/**
 * @author Pavankumar - created date : Sep 13, 2021
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Dairy Management APIs", contact = @Contact(name = "Pure In Fresh", url = "www.pureinfresh.in", email = "info@pureinfresh.in"), version = "1.0"))
@EnableSpringDataWebSupport
public class DairyFarmManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DairyFarmManagementApplication.class, args);
	}

}
