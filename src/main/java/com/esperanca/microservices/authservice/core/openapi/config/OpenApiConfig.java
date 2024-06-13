package com.esperanca.microservices.authservice.core.openapi.config;

import com.esperanca.microservices.authservice.core.openapi.errorresponse.BadCredentialsResponseError;
import com.esperanca.microservices.authservice.core.openapi.errorresponse.InvalidDataResponseError;
import io.swagger.v3.core.converter.ModelConverters;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.media.Schema;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;
import io.swagger.v3.oas.models.servers.Server;
import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

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

    final var components = new Components()
        .schemas(this.generateSchemas());

    return new OpenAPI()
        .info(info)
        .addServersItem(server)
        .components(components);
  }

  @Bean
  public OpenApiCustomizer openApiCustomizer() {
    return openApi -> {
      openApi.getPaths()
          .values()
          .forEach(pathItem -> pathItem.readOperationsMap()
              .forEach((httpMethod, operation) -> {
                final var internalServerError = new ApiResponse();
                internalServerError.description("Internal server error");

                final var resourceNotFound = new ApiResponse();
                resourceNotFound.description("Resource not found");

                final ApiResponses responses = operation.getResponses();

                switch (httpMethod) {
                  case POST:
                    responses.addApiResponse("500", internalServerError);
                    break;

                  case GET:
                    responses.addApiResponse("404", resourceNotFound);
                    responses.addApiResponse("500", internalServerError);
                    break;
                }
              })
          );
    };
  }

  private Map<String, Schema> generateSchemas() {
    final Map<String, Schema> schemaMap = new HashMap<>();

    final Map<String, Schema> invalidDataResponseError = ModelConverters.getInstance().read(InvalidDataResponseError.class);
    final Map<String, Schema> invalidDataResponseError2 = ModelConverters.getInstance().read(InvalidDataResponseError.Field.class);
    final Map<String, Schema> badCredentialsResponseError = ModelConverters.getInstance().read(BadCredentialsResponseError.class);

    schemaMap.putAll(invalidDataResponseError);
    schemaMap.putAll(invalidDataResponseError2);
    schemaMap.putAll(badCredentialsResponseError);

    return schemaMap;
  }
}
