package com.example.ms_remboursement.Clients;

import com.example.ms_remboursement.DTOs.OrdonnanceResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class OrdonnanceClient {

    private final WebClient webClient;

    public OrdonnanceClient(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder
                .baseUrl("http://ms-ordonnance")
                .build();
    }

    public Mono<OrdonnanceResponse> getOrdonnanceById(Long ordonnanceId) {
        return webClient.get()
                .uri("/ordonnances/{id}", ordonnanceId)
                .retrieve()
                .bodyToMono(OrdonnanceResponse.class);
    }
}