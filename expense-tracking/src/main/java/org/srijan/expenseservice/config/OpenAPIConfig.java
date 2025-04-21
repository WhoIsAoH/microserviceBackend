package org.srijan.expenseservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {
    @Value("${app.api-server.url}")
    private String apiServerUrl;

    @Bean
    public OpenAPI openAPI() {
        // api info for swagger documentation
        Info info = new Info()
                .title("FlyNepal Api Service")
                .version("1.0.0")
                .description("Spring Boot API documentation for FlyNepal API Service.");

        // generate dynamic server for swagger i.e. base path
        Server server = new Server().url(apiServerUrl).description("base url");

        // add components
        Components components = new Components()
                .addSecuritySchemes("bearerAuth",
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT"));

        // return openapi generator config
        return new OpenAPI()
                .components(components)
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .info(info)
                .servers(List.of(server));
    }
//    public OpenAPI SecurityServiceAPI() {
//        return new OpenAPI()
//                .info(new Info().title("User Service API")
//                        .description("This is the REST API for User Service")
//                        .version("v0.0.1")
//                        .license(new License().name("Apache 2.0")))
//                .externalDocs(new ExternalDocumentation()
//                        .description("You can refer to the Product Service Wiki Documentation")
//                        .url("https://product-service-dummy-url.com/docs"));
//    }
}