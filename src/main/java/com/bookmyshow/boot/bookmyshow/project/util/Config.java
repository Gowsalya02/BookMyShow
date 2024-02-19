package com.bookmyshow.boot.bookmyshow.project.util;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class Config implements WebMvcConfigurer 
{
	

	@Bean
	public OpenAPI swaggerDocOpenApi()
	{
		Server devServer=new Server();
		devServer.setUrl("localhost:8080");
		devServer.setDescription("Development Server");
		
		Server testServer=new Server();
		testServer.setUrl("localhost:8081");
		testServer.setDescription("Test Server");
		
		Contact contact=new Contact();
		contact.setEmail("bookmyshow@gmail.com");
		contact.setName("devName");
		contact.setUrl("../https://github.com");
		
		
		License license=new License();
		license.setName("License");
		license.setUrl("license providers");
		
		Info info=new Info();
		info.setContact(contact);
		info.setLicense(license);
		info.setDescription("BookMyShow:Project");
		
		info.setTermsOfService("https://www.nature.com/info/terms-and-conditions");
		info.setTitle("Book My Show");
		info.setVersion("2.0");
		
		OpenAPI openApi=new OpenAPI();
		openApi.info(info);
		openApi.servers(Arrays.asList(devServer,testServer));
		return openApi;
	}
}
