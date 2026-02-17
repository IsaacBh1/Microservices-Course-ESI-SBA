package com.example.ms_patient.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

    @Value("${ms.ordonnance.url:http://localhost:8082}")
    private String ordonnanceServiceUrl;

    @Bean
    public HttpGraphQlClient ordonnanceGraphQlClient() {
        WebClient webClient = WebClient.builder()
                .baseUrl(ordonnanceServiceUrl + "/graphql")
                .build();
        return HttpGraphQlClient.builder(webClient).build();
    }
}