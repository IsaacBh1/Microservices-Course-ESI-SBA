package com.example.ms_patient.Controllers;

import com.example.ms_patient.DTOs.PatientDTO;
import com.example.ms_patient.Entities.Patient;
import com.example.ms_patient.Repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    
    private final PatientRepository patientRepository;
    
    @GetMapping("/{id}")
    public PatientDTO getPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findById(id).orElse(null);
        if (patient == null) return null;
        
        PatientDTO dto = new PatientDTO();
        dto.setId(patient.getId());
        dto.setNom(patient.getNom());
        dto.setNumeroSecuriteSocial(patient.getNumeroSecuriteSocial());
        dto.setPlafondRemboursement(patient.getPlafondRemboursement());
        return dto;
    }
}
