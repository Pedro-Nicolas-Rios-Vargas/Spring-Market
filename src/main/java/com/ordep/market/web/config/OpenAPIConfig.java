package com.ordep.market.web.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI api() {
        /*
         * Swagger 2 config code
         * return new Docket(DocumentationType.SWAGGER_2)
         *          .select()
         *          .apis(RequestHandlerSelectors.basePackage("com.ordep.market.web.controller"))
         *          .build();
         */

        /* OpenAPI like Swagger 2 configuration
         * Due to its only one Docket, all the config from swagger is done in application.properties.
         *
         * Look at: https://springdoc.org/#migrating-from-springfox
         */
        return new OpenAPI()
                .info(new Info().title("Simple Market-APP API")
                        .description("Spring market sample application"));
    }
}
