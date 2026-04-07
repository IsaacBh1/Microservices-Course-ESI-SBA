package com.example.ms_remboursement.Clients;

import com.example.ms_remboursement.DTOs.PatientResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class PatientClient {

    private final WebClient webClient;

    public PatientClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://ms-patient")
                .build();
    }

    public Mono<PatientResponse> getPatientById(Long patientId) {
        return webClient.get()
                .uri("/patients/{id}", patientId)
                .retrieve()
                .bodyToMono(PatientResponse.class);
    }
}