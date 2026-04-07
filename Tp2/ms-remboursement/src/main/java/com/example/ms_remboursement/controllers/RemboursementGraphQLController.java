package com.example.ms_remboursement.controllers;

import com.example.ms_remboursement.Clients.OrdonnanceClient;
import com.example.ms_remboursement.DTOs.RemboursementDetailDTO;
import com.example.ms_remboursement.entities.Remboursement;
import com.example.ms_remboursement.repositories.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import reactor.core.publisher.Mono;

@Controller
@RequiredArgsConstructor
public class RemboursementGraphQLController {

    private final RemboursementRepository remboursementRepository;
    private final OrdonnanceClient ordonnanceClient;

    @QueryMapping
    public Mono<RemboursementDetailDTO> remboursementWithOrdonnance(@Argument Long id) {
        Remboursement remb = remboursementRepository.findById(id).orElse(null);
        if (remb == null) {
            return Mono.empty();
        }
        return ordonnanceClient.getOrdonnanceById(remb.getOrdonnanceId())
                .map(ordonnance -> {
                    RemboursementDetailDTO dto = new RemboursementDetailDTO();
                    dto.setId(remb.getId());
                    dto.setDateRemboursement(remb.getDate());
                    dto.setMontant(remb.getMontant());
                    dto.setOrdonnanceId(remb.getOrdonnanceId());
                    dto.setDateOrdonnance(ordonnance.getDate());
                    dto.setNombreMedicaments(ordonnance.getMedicaments().size());
                    if (remb.getPatientAssure() != null) {
                        dto.setNumeroSecuriteSocial(remb.getPatientAssure().getNumeroSecuriteSocial());
                        dto.setPatientNom(remb.getPatientAssure().getNom()); 
                    }
                    return dto;
                });
    }
}