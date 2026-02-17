package com.example.ms_patient.Controllers;

import com.example.ms_patient.Clients.OrdonnanceGraphQLClient;
import com.example.ms_patient.DTOs.OrdonnanceDTO;
import com.example.ms_patient.DTOs.PatientDTO;
import com.example.ms_patient.DTOs.PatientWithMedicamentsDTO;
import com.example.ms_patient.Entities.Patient;
import com.example.ms_patient.Repositories.PatientRepository;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {
    
    private final PatientRepository patientRepository;
    private final OrdonnanceGraphQLClient ordonnanceGraphQLClient;

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

    @GetMapping("/{patientId}/ordonnances")
    public ResponseEntity<List<OrdonnanceDTO>> getOrdonnancesByPatient(@PathVariable Long patientId) {
        List<OrdonnanceDTO> ordonnances = ordonnanceGraphQLClient.getOrdonnancesByPatientId(patientId);
        return ResponseEntity.ok(ordonnances);
    }

    @GetMapping("/{patientId}/with-ordonnances")
    public ResponseEntity<PatientWithMedicamentsDTO> getOrdonnancesByPatientWithOrdonnances(@PathVariable Long patientId) {
        Patient patient = patientRepository.findById(patientId).orElse(null);


        List<OrdonnanceDTO> ordonnances = ordonnanceGraphQLClient.getOrdonnancesByPatientId(patientId);
        OrdonnanceDTO ordonnanceDTO = ordonnances.get(0);

        PatientWithMedicamentsDTO patientWithMedicamentsDTO = new PatientWithMedicamentsDTO();
        patientWithMedicamentsDTO.setId(patient.getId());
        patientWithMedicamentsDTO.setNom(patient.getNom());
        patientWithMedicamentsDTO.setNumeroSecuriteSocial(patient.getNumeroSecuriteSocial());
        patientWithMedicamentsDTO.setPlafondRemboursement(patient.getPlafondRemboursement());
        patientWithMedicamentsDTO.setMedicaments(ordonnanceDTO.getMedicaments());

        return ResponseEntity.ok(patientWithMedicamentsDTO);
    }

}
