package com.example.ms_remboursement.controllers;

import com.example.ms_remboursement.Clients.PatientClient;
import com.example.ms_remboursement.DTOs.RemboursementDTO;
import com.example.ms_remboursement.entities.Remboursement;
import com.example.ms_remboursement.repositories.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remboursements")
@RequiredArgsConstructor
public class RemboursementController {
    
    private final RemboursementRepository remboursementRepository;
    private final PatientClient patientClient;

    @GetMapping
    public List<Remboursement> getAllRemboursements() {
        return remboursementRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Remboursement getRemboursement(@PathVariable Long id) {
        return remboursementRepository.findById(id).orElse(null);
    }
    
    @GetMapping("/ordonnance/{ordonnanceId}")
    public RemboursementDTO getRemboursementByOrdonnance(@PathVariable Long ordonnanceId) {
        Remboursement remb = remboursementRepository.findByOrdonnanceId(ordonnanceId).orElse(null);
        if (remb == null) return null;
        
        RemboursementDTO dto = new RemboursementDTO();
        dto.setId(remb.getId());
        dto.setDate(remb.getDate().toString());
        dto.setMontant(remb.getMontant());
        dto.setOrdonnanceId(remb.getOrdonnanceId());
        
        if (remb.getPatientAssure() != null) {
            dto.setNumeroSecuriteSocial(remb.getPatientAssure().getNumeroSecuriteSocial());
        }
        
        return dto;
    }
    @GetMapping("/{id}/patient-name")
    public Mono<String> getPatientNameForRemboursement(@PathVariable Long id) {
        Remboursement remb = remboursementRepository.findById(id).orElse(null);
        if (remb == null || remb.getPatientAssure() == null) {
            return Mono.just("Inconnu");
        }
        Long patientId = remb.getPatientAssure().getPatientId(); 
        return patientClient.getPatientById(patientId)
                .map(patient -> patient.getNom() + " " + patient.getPrenom())
                .defaultIfEmpty("Patient non trouvé");

    
    }
    @GetMapping("/{id}/local-patient-name")
    public String getLocalPatientName(@PathVariable Long id) {
    return remboursementRepository.findById(id)
            .map(r -> r.getPatientAssure() != null ? r.getPatientAssure().getNom() : "Inconnu")
            .orElse("Remboursement introuvable");
    }
}
