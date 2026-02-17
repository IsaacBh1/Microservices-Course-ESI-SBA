package com.example.ms_patient.Controllers;

import com.example.ms_patient.Clients.OrdonnanceClient;
import com.example.ms_patient.Clients.RemboursementClient;
import com.example.ms_patient.DTOs.MedicamentDTO;
import com.example.ms_patient.DTOs.OrdonnanceDetailDTO;
import com.example.ms_patient.DTOs.RemboursementDTO;
import com.example.ms_patient.Entities.Patient;
import com.example.ms_patient.Repositories.PatientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class PatientGraphQLController {
    
    private final PatientRepository patientRepository;
    private final OrdonnanceClient ordonnanceClient;
    private final RemboursementClient remboursementClient;
    
    
    
    @QueryMapping
    public List<Patient> patients() {
        return patientRepository.findAll();
    }
    
    @QueryMapping
    public Patient patient(@Argument Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    
    @QueryMapping
    public Patient patientWithOrdonnances(@Argument Long id) {
        return patientRepository.findById(id).orElse(null);
    }
    
    @SchemaMapping(typeName = "Patient", field = "ordonnances")
    public List<OrdonnanceDetailDTO> getOrdonnances(Patient patient) {
        try {
            var ordonnances = ordonnanceClient.getOrdonnancesByPatient(patient.getId());
            return ordonnances.stream().map(ord -> {
                var detail = new OrdonnanceDetailDTO();
                detail.setIdOrdonnance(ord.getIdOrdonnance());
                detail.setMedicaments(ord.getMedicaments().stream()
                    .map(med -> {
                        var medicament = new MedicamentDTO();
                        medicament.setNom(med.getNom());
                        medicament.setCout(med.getCout());
                        return medicament;
                    })
                    .collect(Collectors.toList()));
                
                try {
                    var remb = remboursementClient.getRemboursementByOrdonnance(ord.getIdOrdonnance());
                    if (remb != null) {
                        var rembDTO = new RemboursementDTO();
                        rembDTO.setMontant(remb.getMontant());
                        rembDTO.setDate(remb.getDate());
                        detail.setRemboursement(rembDTO);
                    }
                } catch (Exception e) {

                    System.out.println(e.toString());
                }
                
                return detail;
            }).collect(Collectors.toList());
        } catch (Exception e) {
            return List.of();
        }
    }
}