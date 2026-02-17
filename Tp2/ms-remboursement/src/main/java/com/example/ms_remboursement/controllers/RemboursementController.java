package com.example.ms_remboursement.controllers;

import com.example.ms_remboursement.DTOs.RemboursementDTO;
import com.example.ms_remboursement.entities.Remboursement;
import com.example.ms_remboursement.repositories.RemboursementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/remboursements")
@RequiredArgsConstructor
public class RemboursementController {
    
    private final RemboursementRepository remboursementRepository;
    
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
}
