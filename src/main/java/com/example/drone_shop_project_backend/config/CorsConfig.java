package com.example.drone_shop_project_backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig
{
    @Bean
    public WebMvcConfigurer corsConfigurer()
    {
        // Annotating this class as a Spring configuration class.
        // It's used to define Spring beans and application configuration.

        // Enabling Spring Web MVC in this configuration class.
        // Indicates that this class is configuring Spring Web MVC.

        return new WebMvcConfigurer() {
            // Defining a Spring bean named "corsConfigurer" of type WebMvcConfigurer.
            // This bean is responsible for configuring CORS (Cross-Origin Resource Sharing).

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Overriding the method to configure CORS settings.

                // Allowing cross-origin requests for all paths in the application.
                // Requests from any origin can access resources in this application.

                registry.addMapping("/**")
                        // This line specifies the allowed origins, which are the domains or URLs that are permitted
                        // to make cross-origin requests to this application.

                        // Specifying the allowed origins (domains/URLs) for cross-origin requests.
                        // Requests from "http://localhost:3000" and "http://127.0.0.1:3000" are permitted.

                        .allowedOrigins("http://localhost:3000/", "http://127.0.0.1:3000/")

                        // Specifying the allowed origins (domains/URLs) for cross-origin requests.
                        // Requests from "http://localhost:3000" and "http://127.0.0.1:3000" are permitted.


                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")

                // Specifying the allowed HTTP methods for cross-origin requests.
                // Only the specified HTTP methods (GET, POST, PUT, DELETE, OPTIONS) are allowed.
                ;
            }
        };
    }
}