package com.example.ms_ordonnance.clients;

import com.example.ms_ordonnance.DTOs.RemboursementResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class RemboursementWebClient {

    private final WebClient webClient;

    public RemboursementWebClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://ms-remboursement")
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