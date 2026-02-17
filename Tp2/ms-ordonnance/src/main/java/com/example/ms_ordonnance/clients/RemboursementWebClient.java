package com.example.ms_ordonnance.clients;

import com.example.ms_ordonnance.DTOs.RemboursementResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RemboursementWebClient {

    private final WebClient webClient;

    public RemboursementWebClient(@Value("${ms.remboursement.url:http://localhost:8083}") String baseUrl) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public RemboursementResponse getRemboursementByOrdonnance(Long ordonnanceId) {
        return webClient.get()
                .uri("/remboursements/ordonnance/{ordonnanceId}", ordonnanceId)
                .retrieve()
                .bodyToMono(RemboursementResponse.class)
                .block();
    }
}