package com.esperanca.microservices.authservice.core.openapi;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

  @Bean
  public OpenAPI openAPI() {
    final var contact = new Contact()
        .name("Support Team")
        .email("support@authservice.com")
        .url("https://authservice.com/support");

    final var info = new Info()
        .version("1.0")
        .contact(contact)
        .title("Auth Service")
        .description("Some description")
        .termsOfService("https://authservice.com/terms");

    final var server = new Server()
        .url("localhost:8080")
        .description("Development server");

    return new OpenAPI()
        .info(info)
        .addServersItem(server);
  }
}
