package com.app.shopping.ecommerce;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import java.util.Collections;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "E-Commerce API",
				description = "E-Commerce API",
				version = "1.0"
		),
		externalDocs = @ExternalDocumentation(
				description = "E-Commerce API",
				url = "https://github.com/mariappan-sivakumar-zuci/E-Commerce"))
public class ECommerceApplication {

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}


	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
