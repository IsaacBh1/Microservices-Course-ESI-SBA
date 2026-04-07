package com.example.ms_ordonnance.clients;

import com.example.ms_ordonnance.DTOs.PatientResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PatientWebClient {

    private final WebClient webClient;

    public PatientWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://ms-patient")   // service name registered in Eureka
                .build();
    }

    public PatientResponse getPatient(Long id) {
        return webClient.get()
                .uri("/api/patients/{id}", id)
                .retrieve()
                .bodyToMono(PatientResponse.class)
                .block();
    }
}