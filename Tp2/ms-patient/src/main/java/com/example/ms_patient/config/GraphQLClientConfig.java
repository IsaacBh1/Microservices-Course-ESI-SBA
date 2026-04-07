package com.example.ms_patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.client.HttpGraphQlClient;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GraphQLClientConfig {

    @Bean
    public HttpGraphQlClient ordonnanceGraphQlClient(WebClient.Builder webClientBuilder) {
        WebClient webClient = webClientBuilder
                .baseUrl("http://ms-ordonnance/graphql")
                .build();
        return HttpGraphQlClient.builder(webClient).build();
    }
}