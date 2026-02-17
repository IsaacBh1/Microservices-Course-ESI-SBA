package com.example.ms_ordonnance.clients;

import com.example.ms_ordonnance.DTOs.PatientResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class PatientWebClient {

    private final WebClient webClient;

    public PatientWebClient(@Value("${ms.patient.url:http://localhost:8080}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
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