package com.example.productservice.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {


    // todo: fill real data
    @Bean
    public OpenAPI myOpenAPI() {

        Contact contact = new Contact();
        contact.setEmail("esgerlinihad7@gmail.com");
        contact.setName("Nihad");

        Info info = new Info()
                .title("MicroService Sale")
                .version("1.0")
                .contact(contact)
                .description("This API exposes endpoints to manage tutorials.");

        return new OpenAPI().info(info);
    }
}