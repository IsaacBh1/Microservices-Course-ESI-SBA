package com.example.ms_ordonnance.controllers;

import com.example.ms_ordonnance.DTOs.MedicamentDTO;
import com.example.ms_ordonnance.DTOs.MedicamentResponse;
import com.example.ms_ordonnance.DTOs.OrdonnanceResponse;
import com.example.ms_ordonnance.DTOs.OrdonnanceWithPatientDTO;
import com.example.ms_ordonnance.DTOs.OrdonnanceWithRemboursementDTO;
import com.example.ms_ordonnance.clients.PatientWebClient;
import com.example.ms_ordonnance.clients.RemboursementWebClient;
import com.example.ms_ordonnance.entities.Ordonnance;
import com.example.ms_ordonnance.repositories.OrdonnanceRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ordonnances")
@RequiredArgsConstructor
public class OrdonnanceController {
    
    private final OrdonnanceRepository ordonnanceRepository;
    private final RemboursementWebClient remboursementWebClient;
    private final PatientWebClient patientWebClient;
    
    @GetMapping
    public List<Ordonnance> getAllOrdonnances() {
        return ordonnanceRepository.findAll();
    }
    
    @GetMapping("/{id}")
    public Ordonnance getOrdonnance(@PathVariable Long id) {
        return ordonnanceRepository.findById(id).orElse(null);
    }
    
    @GetMapping("/patient/{patientId}")
    public List<OrdonnanceResponse> getOrdonnancesByPatient(@PathVariable Long patientId) {
        List<Ordonnance> ordonnances = ordonnanceRepository.findByPatientId(patientId);
        return ordonnances.stream()
            .map(ord -> {
                OrdonnanceResponse response = new OrdonnanceResponse();
                response.setIdOrdonnance(ord.getId());
                response.setDate(ord.getDate().toString());
                response.setMedicaments(ord.getMedicaments().stream()
                    .map(med -> {
                        MedicamentResponse medResp = new MedicamentResponse();
                        medResp.setNom(med.getNomMedicament());
                        medResp.setCout(med.getCout());
                        return medResp;
                    })
                    .collect(Collectors.toList()));
                return response;
            })
            .collect(Collectors.toList());
    }
    
     @GetMapping("/{id}/with-patient")
    public OrdonnanceWithPatientDTO getOrdonnanceWithPatient(@PathVariable Long id) {
        Ordonnance ordonnance = ordonnanceRepository.findById(id).orElse(null);
        if (ordonnance == null) return null;
        
        OrdonnanceWithPatientDTO dto = new OrdonnanceWithPatientDTO();
        dto.setId(ordonnance.getId());
        dto.setDate(ordonnance.getDate().toString());
        
        try {
            var patient = patientWebClient.getPatient(ordonnance.getPatientId());  // ⬅️ Utilisez WebClient
            dto.setPatientNom(patient.getNom());
        } catch (Exception e) {
            dto.setPatientNom("Patient non trouvé");
            e.printStackTrace();
        }
        
        dto.setMedicaments(ordonnance.getMedicaments().stream()
            .map(med -> {
                MedicamentDTO medDTO = new MedicamentDTO();
                medDTO.setNomMedicament(med.getNomMedicament());
                medDTO.setCout(med.getCout());
                medDTO.setDosage(med.getDosage());
                return medDTO;
            })
            .collect(Collectors.toList()));
        
        return dto;
    }

   @GetMapping("/{id}/with-remboursement")
    public OrdonnanceWithRemboursementDTO getOrdonnanceWithRemboursement(@PathVariable Long id) {
        Ordonnance ordonnance = ordonnanceRepository.findById(id).orElse(null);
        if (ordonnance == null) return null;

        OrdonnanceWithRemboursementDTO dto = new OrdonnanceWithRemboursementDTO();
        dto.setId(ordonnance.getId());
        dto.setDate(ordonnance.getDate().toString());
        
        // Changez patientClient par patientWebClient
        try {
            var patient = patientWebClient.getPatient(ordonnance.getPatientId());
            dto.setNumeroSecuriteSocial(patient.getNumeroSecuriteSocial());
        } catch (Exception e) {
            dto.setNumeroSecuriteSocial("N/A");
            System.err.println("Erreur Patient: " + e.getMessage());
        }
        
        // Changez remboursementClient par remboursementWebClient
        try {
            var remboursement = remboursementWebClient.getRemboursementByOrdonnance(id);
            dto.setMontantRemboursement(remboursement.getMontant());
            dto.setDateRemboursement(remboursement.getDate());
        } catch (Exception e) {
            dto.setMontantRemboursement(0.0);
            dto.setDateRemboursement("N/A");
            System.err.println("Erreur Remboursement: " + e.getMessage());
        }
        
        dto.setMedicaments(ordonnance.getMedicaments().stream()
            .map(med -> {
                MedicamentDTO medDTO = new MedicamentDTO();
                medDTO.setNomMedicament(med.getNomMedicament());
                medDTO.setCout(med.getCout());
                medDTO.setDosage(med.getDosage());
                return medDTO;
            })
            .collect(Collectors.toList()));
        
        return dto;
    }
}
